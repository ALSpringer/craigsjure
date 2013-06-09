/*globals postMessage, self*/
var page = '<div data-role="page" id="thepage" class="simplepage"><div data-role="header" data-position="fixed" data-tap-toggle="false"><h1>This is a simple header</h1></div><div data-role="content"><ul data-role="listview" data-inset="true">';
self.addEventListener("message", function (e) {
  for (var i = 0; i < e.data.results.length; i++) {
    page += '<li><img src="' + e.data.results[i].profile_image_url + '"><h2>' + e.data.results[i].from_user_name + '</h2><p>' + e.data.results[i].text + '</p><p class="ui-li-aside">' + e.data.results[i].created_at + '</p></li>';
  }
  page += '</ul></div></div>';
  postMessage(page);
});
