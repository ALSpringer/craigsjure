var NUM_WORKERS = 100;
$(document).on('pageinit', '#startpage', function () {
  var startTime = 0,
      workers = [],
      first = true,
      count = 0,
      body = $('body'),
      page = '<div data-role="page" id="thepage" class="simplepage"><div data-role="header" data-position="fixed" data-tap-toggle="false"><h1>This is a simple header</h1></div><div data-role="content"><ul data-role="listview" data-inset="true">';
  
  $.get('http://search.twitter.com/search.json?q=balling&rpp=20', function (data) {
    startTime = Date.now();
    for (var i = 0; i < e.data.results.length; i++) {
      page += '<li><img src="' + e.data.results[i].profile_image_url + '"><h2>' + e.data.results[i].from_user_name + '</h2><p>' + e.data.results[i].text + '</p><p class="ui-li-aside">' + e.data.results[i].created_at + '</p></li>';
    }
    page += '</ul></div></div>';
    for (var i = 0; i < NUM_WORKERS; i++) {
      workers[i] = new Worker('js/createSimplePage.js');
    }
    for (var k = 0; k < NUM_WORKERS; k++) {
      workers[k].postMessage(data);
    }
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
