String.prototype.format = String.prototype.f = function(){
	var args = arguments;
	return this.replace(/\{(\d+)\}/g, function(m,n){
		return args[n] ? args[n] : m;
	});
};

$.ajax({
  url: "https://dl.dropboxusercontent.com/u/20755008/response.json",
  success: function( result ) {
  	var json = $.parseJSON(result);
  	var container = $('#stats');

  	json.forEach(function (item, i, arr) {
  		container.append("<span>{0} {1} => {2}, +{3}, -{4}</span><br>".f(item.user, item.name ? "[" + item.name + "]" : " ", item.repo_data.total, item.repo_data.additions, item.repo_data.deletions ? item.repo_data.deletions : "0"));
  	});
  }
});