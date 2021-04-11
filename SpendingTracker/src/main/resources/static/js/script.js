window.addEventListener('load', function(evt){
    console.log('script.js loaded');
    init();
});

function init() {
    getAllPurchases();
    document.purchaseForm.submit.addEventListener('click', createPurchase);
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
    let element = document.createElement('h3');
    element.textContent = msg;
    dataDiv.appendChild(element);
}

function displayPurchases(purchases) {
    let div = document.getElementById('mainTable');
    //TODO: click event for detail view

    for(const purchase of purchases) {
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
    console.log(id);

    let xhr = new XMLHttpRequest();
    xhr.open('DELETE', `api/purchase/${id}`);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            console.log(xhr.status);
          if (xhr.status === 204 || xhr.status === 202) {
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