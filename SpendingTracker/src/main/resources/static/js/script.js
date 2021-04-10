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
        notes.textContent=purchase.notes;
        tr.appendChild(notes);
        div.appendChild(tr);
    }
    
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