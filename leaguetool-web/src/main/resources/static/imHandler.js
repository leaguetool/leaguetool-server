var IMHandler = function () {
  
  this.onopen = function (event, ws) {
    // ws.send('hello 连上了哦')
    //document.getElementById('contentId').innerHTML += 'hello 连上了哦<br>';
  }

  /**
   * 收到服务器发来的消息
   * @param {*} event 
   * @param {*} ws 
   */
  this.onmessage = function (event, ws) {
    var data = event.data;
    var msgBody=eval('(' + data + ')');
    var ico,imgcls,spancls,ncikcls;
    var content = document.getElementsByTagName('ul')[0];

    if(msgBody.type == 'CHAT'){
        msgBody = JSON.parse(msgBody.data);
        msgBody.message = msgBody.content;
    }
    if (myname==msgBody.name)
	{
    	ico="image/t2.jpg";
    	imgcls="imgright";
    	spancls="spanright";
    	nickcls="nickright";
	}else{
		ico=msgBody.avatar;
    	imgcls="imgleft";
    	spancls="spanleft";
    	nickcls="nickleft"
	}
    console.log(myname)
    //myself=!myself;
    if (msgBody.name=="admin")
    	{
    	content.innerHTML += '<li><div class="sysinfo">'+msgBody.message+'</div></li>';
    	}else
		{
    		content.innerHTML += '<li><img src="'+ico+'" class="'+imgcls+'"><span class="'+nickcls+'">'+msgBody.name+'</span><span class="'+spancls+'">'+msgBody.message+'</span></li>';
		}
    
    //document.getElementById('contentId').innerHTML += data + '<br>'
    content.scrollTop=content.scrollHeight; 
  }

  this.onclose = function (e, ws) {
    // error(e, ws)
  }

  this.onerror = function (e, ws) {
    // error(e, ws)
  }

  /**
   * 发送心跳，本框架会自动定时调用该方法，请在该方法中发送心跳
   * @param {*} ws 
   */
  this.ping = function (ws) {
    // log("发心跳了")
    ws.send(JSON.stringify({data: null, type: "ping"}))
  }
}
