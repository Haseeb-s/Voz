<html>
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
  sendAjax();
});
 
function sendAjax() {
 
$.ajax({ 
    url: "/Voz/Post/addPost", 
    type: 'POST', 
    dataType: 'json', 
    data: "{
    	name: "my Post",
    	fields: [{key: "name", value: "my name"},{key: "Category", value: "Food"}],
    	Date: "December 16, 2015",
    	Location: {lat: 233, lng: 666},
    	Id: "sdfjnskfjn238932uriufdh"
    	}", 
    contentType: 'application/json',
    mimeType: 'application/json',
    success: function(data) { 
        alert(data.id + " " + data.name);
    },
    error:function(data,status,er) { 
        alert("error: "+data+" status: "+status+" er:"+er);
    }
});
}
</script>
</html>