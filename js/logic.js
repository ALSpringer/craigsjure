var activities = [];

function createActivity(activityName) {
  var exists = false;
  for (var i in activities) {
    if (activities[i].name === activityName) {
      exists = true;
    }
  }
  if (!exists) {
    return activities.push(
    { name      : activityName,
      totalTime : 0,
      totalUses : 0
    });
  }
  else {
    $("#samenamepopup").popup("open");
  }
}

function createEditPage() {
  $("#addactivity").on("vclick", function () {
    if ($("#")){}
  }
}

$(document).on("pageinit", function (event) {
  switch(event.target.id) {
    case "editpage":
      createEditPage();
      break;
  }
});
