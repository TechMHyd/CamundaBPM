// fetch execution variables
var response = connector.getVariable("response");
var date = connector.getVariable("date");

// parse response variable with camunda-spin
var spinRes = S(response);

//var query = "$.[?(@.isVeg=='" + date + "')]";

//var itemName = spinRes.jsonPath("$.items[2].itemName").stringValue();

var items = spinRes.jsonPath("$.items").elementList();
// use camunda-spin jsonPath to test if date is a holiday
//!holidays.jsonPath(query).elementList().isEmpty();
var isListEmpty = items.isEmpty();
!isListEmpty