var exec = require('cordova/exec');

function FCMPlugin()
{

}
FCMPlugin.prototype.pInvoke = function(method,data,callbackOk,callbackError)
{
   if(data == null || data == undefined)
   {
       data =[];
   }
   else if(!Array.isArray(data))
   {
       data = [data];
   }
       exec(callbackOk, callbackError, "FCMPlugin",method,data);

}

FCMPlugin.prototype.getToken = function(data, callbackOk, callbackError) {
	this.pInvoke("getToken",data,callbackOk,callbackError)
};

FCMPlugin.prototype.registerNotification = function(data, callbackOk, callbackError) {
		this.pInvoke("registerNotification",data,callbackOk,callbackError)

};

FCMPlugin.prototype.ready = function(data, callbackOk, callbackError) {
		this.pInvoke("ready",[],callbackOk,callbackError)

};
FCMPlugin.install = function()
{
	if(!window.plugins)
	{
		window.plugins = {};
	}
	cordova.plugins.FCMPlugin = new FCMPlugin();
	return cordova.plugins.FCMPlugin;
}

cordova.addConstructor(FCMPlugin.install);