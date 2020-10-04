var total = 0;
var itemID = '';
var ds = new DataService();

// click functions //////////////////////////////////////////////////////////

function onItemClick(e) {
    e.preventDefault();

    var item = $(this);
    itemID = item.data("itemid");
    var itemNum = item.data("itemnumber");
    //console.log(itemNum);
    $("#itemIDOutput").val(itemNum);
};

function onBalanceClick(e) {
    e.preventDefault();

    var balance = $(this);
    var balanceAmt = balance.data("balanceid");
    total = total + balanceAmt;
    $("#balanceOutput").val((total / 100).toFixed(2));
}

function onPurchaseClick(e) {
    e.preventDefault();

    ds.getItemByID(itemID, (total / 100), onGetItemByIDSuccess, logError);

    //reloading items with new quantities 
    $(".items-menu").empty();
    ds.getAllItems(onGetAllItemsSuccess, logError);

    $("#balanceOutput").val((total / 100).toFixed(2));

}

function onChangeClick(e) {
    e.preventDefault();

    total = 0;
    itemID = '';
    resetForms();
    ds.getAllItems(onGetAllItemsSuccess, logError);
}

// success functions //////////////////////////////////////////////////////////

function onGetAllItemsSuccess(response) {
    $(".items-menu").empty();
    for (var i = 0; i < response.length; i++) {
        var itemFormat =
            `<div class="vendingItem" data-itemID="${response[i].id}" data-itemNumber="${i + 1}">
        <p class="item-data item-number" id="item-number">${i + 1}</p>
        <p class="item-data item-name">${response[i].name}</p>
        <p class="item-data item-price">$${response[i].price.toFixed(2)}</p>
        <p class="item-data item-quantity">Quantity Left: ${response[i].quantity}</p>
    </div>`

        $(".items-menu").append(itemFormat);
    }
}

function onGetItemByIDSuccess(response) {
    $("#balanceOutput").val("0.00");
    $("#messageOutput").val("Thank You!!!");

    //displaying change 

    var quarter = response.quarters;
    var dime = response.dimes;
    var nickel = response.nickels;
    var penny = response.pennies;

    var changeAsString = '';

    if (quarter > 1) {
        changeAsString += quarter + " quarters";
    }

    if (quarter == 1) {
        changeAsString += quarter + " quarter";
    }

    if (quarter >= 1 && (dime >= 1 || nickel >= 1 || penny >= 1)) {
        changeAsString += ", ";
    }

    if (dime > 1) {
        changeAsString += dime + " dimes";
    }

    if (dime == 1) {
        changeAsString += dime + " dime";
    }

    if (dime >= 1 && (nickel >= 1 || penny >= 1)) {
        changeAsString += ", ";
    }

    if (nickel > 1) {
        changeAsString += nickel + " nickels";
    }

    if (nickel == 1) {
        changeAsString += nickel + " nickel";
    }

    if (nickel >= 1 && penny >= 1) {
        changeAsString += ", ";
    }

    if (penny > 1) {
        changeAsString += penny + " pennies";
    }

    if (penny == 1) {
        changeAsString += penny + " penny";
    }

    $("#changeOutput").val(changeAsString);

}

// error function //////////////////////////////////////////////////////////

function logError(err) {
    $("#messageOutput").val(err.responseJSON.message);
}

// helper functions //////////////////////////////////////////////////////////

function resetForms() {
    $("#balanceOutput").val("");
    $("#messageOutput").val("");
    $("#itemIDOutput").val("");
    $("#changeOutput").val("");
}

// doc ready functions (controller) //////////////////////////////////////////////////////////

$(document).ready(function () {
    total = 0;
    itemID = '';

    resetForms();
    ds.getAllItems(onGetAllItemsSuccess, logError);

    $(document).on("click", ".vendingItem", onItemClick);
    $(document).on("click", ".balanceButton", onBalanceClick);
    $(document).on("click", ".purchaseButton", onPurchaseClick);
    $(document).on("click", "#changeButton", onChangeClick);
});



