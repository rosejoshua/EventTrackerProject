window.addEventListener('load', function(evt){
    console.log('script.js loaded');
    init();
});

function init() {
    document.purchaseForm.submit.addEventListener('click', createPurchase);
    getAllPurchases();
}

function getAllPurchases() {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', 'api/purchases');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if(xhr.status===200) {
                let purchases = JSON.parse(xhr.responseText);
                displayPurchases(purchases);
            }
            else {
                displayError('Error retrieving purchases: ' + xhr.status);
            }
        }
    }

    xhr.send();
}

function getPurchase(purchaseId) {
    let xhr = new XMLHttpRequest();
    xhr.open('GET', `api/purchase/${purchaseId}`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if(xhr.status===200) {
                let purchase = JSON.parse(xhr.responseText);
                displayPurchase(purchase);
            }
            else {
                displayError('Error retrieving purchases: ' + xhr.status);
            }
        }
    }

    xhr.send();
}

function displayError(msg) {
    var dataDiv = document.getElementById('mainTable');
    dataDiv.textContent = '';
    let element = document.createElement('agg1');
    element.textContent = msg;
    dataDiv.appendChild(element);
}

function displayPurchases(purchases) {
    let div = document.getElementById('mainTable');
    let bodyElement = document.getElementById('body');

    let totalPurchases = 0;


    for(const purchase of purchases) {
        totalPurchases +=purchase.amount;

        let tr = document.createElement('tr');
        let cost = document.createElement('td');
        cost.textContent=purchase.amount;
        tr.appendChild(cost);
        let category = document.createElement('td');
        category.textContent=purchase.category.name;
        tr.appendChild(category);
        let notes = document.createElement('td');
        notes.colSpan=2;
        notes.textContent=purchase.notes;
        tr.appendChild(notes);
        div.appendChild(tr);
        tr.id=purchase.id;
        tr.addEventListener('click', function(event) {
            event.preventDefault();
            var purchaseId = tr.id;
            if (!isNaN(purchaseId) && purchaseId > 0) {
              getPurchase(purchaseId);
            }
          });
    }

    let agg1 = document.createElement('h3');
    agg1.textContent = 'Total of all Purchases: ' + totalPurchases;
    agg1.style = 'margin-left: auto; margin-right: auto; text-align: center';

    bodyElement.appendChild(agg1);
    
    let agg2 = document.createElement('h3');
    agg2.textContent = 'Average purchase: ' + (totalPurchases/div.rows.length-2);
    agg2.style = 'margin-left: auto; margin-right: auto; text-align: center';

    bodyElement.appendChild(agg2);

    console.log(div.rows.length-2);
}

function displayPurchase(purchase) {
    let div = document.getElementById('mainTable');

    for(var x = 2; x < div.rows.length;) {
        div.deleteRow(x);
    }
    
    let deleteButton = document.createElement('input');
    deleteButton.type='button';
    deleteButton.name='delete';
    deleteButton.value='Delete';
    deleteButton.id='delete';
    deleteButton.confirm

    let deletetd = document.createElement('td');
    deletetd.appendChild(deleteButton);
    
    let formRow = document.getElementById('formRow');
    formRow.appendChild(deletetd);

    deleteButton.addEventListener('click', function (event) {
        event.preventDefault();
        var purchaseIdToDelete = purchase.id;
        deletePurchase(purchaseIdToDelete);
    });

    let formElement = document.getElementById('purchaseForm');

    formElement.cost.value=purchase.amount;
    formElement.category.value=purchase.category.id;
    formElement.notes.value=purchase.notes;
    formElement.submit.value='Update';
    
    var editedPurchase = {
        "id": purchase.id,
        "amount": formElement.cost.value,
        "datetime": purchase.datetime,
        "notes": formElement.notes.value,
        "category": {
            "id": formElement.category.id.value
        }
    };

    document.purchaseForm.submit.removeEventListener('click', createPurchase);    
    document.purchaseForm.submit.addEventListener('click', function(event) {
        event.preventDefault();
        console.log(editedPurchase);
        editPurchase(editedPurchase);
      });
    
}

function createPurchase(evt) {
    evt.preventDefault();
    console.log('submit pressed');
    let form = document.purchaseForm;
    let purchase = {
        amount: form.cost.value,
        notes: form.notes.value,
        category: {
            id: form.category.value
        }
    };
    postPurchase(purchase);
}

function deletePurchase(id) {
    console.log('delete pressed');

    let xhr = new XMLHttpRequest();
    xhr.open('DELETE', `api/purchase/${id}`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            console.log(xhr.status);
          if (xhr.status === 204 || xhr.status === 200) {
            console.log('success deleting purchase');
            window.location = "index.html";
          } else {
            displayError('Error deleting purchase: ' + xhr.status);
          }
        }
      };
      xhr.setRequestHeader("Content-type", "application/json");
      xhr.send();
}

function editPurchase(purchase) {
    console.log(purchase.textContent);
    let xhr = new XMLHttpRequest();
    xhr.open('PUT', `api/purchase/${purchase.id}`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
          if (xhr.status === 204 || xhr.status === 200) {
            console.log('success editing purchase');
            window.location = "index.html";
          } else {
            displayError('Error editing purchase: ' + xhr.status);
          }
        }
      };
      xhr.setRequestHeader("Content-type", "application/json");
      xhr.send(JSON.stringify(purchase));
}

function postPurchase(purchase) {
    console.log('Posting purchase');
    console.log(purchase);
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'api/purchases');
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        if (xhr.status === 201 || xhr.status === 200) {
          console.log('success creating purchase');
          window.location = "index.html";
        } else {
          displayError('Error creating purchase: ' + xhr.status);
        }
      }
    };
    xhr.setRequestHeader("Content-type", "application/json");
    xhr.send(JSON.stringify(purchase));
  }