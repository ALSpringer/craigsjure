var NUM_PAGES = 100;
$(document).on('pageinit', '#startpage', function () {
  var startTime = 0,
      workers = [],
      first = true,
      count = 0;

  $.get('http://search.twitter.com/search.json?q=balling&rpp=20', function (data) {
    startTime = Date.now();
    for (var i = 0; i < NUM_WORKERS; i++) {
      workers[i] = new Worker('js/createSimplePage.js');
    }
    for (var k = 0; k < NUM_WORKERS; k++) {
      workers[k].postMessage(data);
    }
    var body = $('body');
    for (var j = 0; j < NUM_WORKERS; j++) {
      workers[j].addEventListener('message', function (e) {
        body.append(e.data);
        if (count === 99) {
          console.log((Date.now() - startTime));
        }
        count++;
        if (first) {
          $.mobile.changePage('#thepage');
          first = false;
        }
      });
    }
  });
});
