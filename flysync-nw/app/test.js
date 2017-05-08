var gui = require("nw.gui");
var win = gui.Window.get();
win.showDevTools();

var downloader = require("./test/downloader");
console.log(process.env.USERPROFILE );
downloader.add({
    "url" : "http://127.0.0.1/a.zip",
    "filename" : "a.zip",
   // "location" : "D:/test",
    "end" : function(){
        console.log("end");
    },
    "error" : function(){
        console.log("erroe")
    },
    "progress" : function(per){
        console.log(per);
    }

});


