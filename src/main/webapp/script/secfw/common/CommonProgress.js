/**
 * 파     일     명 : CommonProgress.js
 * 프로그램명 : 로그인현황
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.12
 */

/* 처리중 모달 open */
var newWin = null;
function progressWinOpen(msg, targetFrame, left, top)
{
	var progressLeft	= window.screenLeft	+ left;
	var progressTop		= window.screenTop	+ top;
	
	if( document.all.ProgressLayer1!=null){
    	progressLayerDisplay('show');
    }
}

/* 처리중 모달 open */
var newWin = null;
function progressWinOpenMsg(msg, targetFrame, left, top)
{
	var progressLeft	= window.screenLeft	+ left;
	var progressTop		= window.screenTop	+ top;
	if( document.all.ProgressLayer1!=null){
    	progressLayerDisplay('show');
    }
}

/*메세지 없는 모달 open */
function progressWinOpen( targetFrame, left, top)
{
	var progressLeft	= window.screenLeft	+ left;
	var progressTop		= window.screenTop	+ top;
	if( document.all.ProgressLayer1!=null)
	{
    	progressLayerDisplay('show');
		var objProgress = document.all.ProgressLayer1;
		var progressPos = 
		{
			x : parseInt(objProgress.style.left), 
			y : parseInt(objProgress.style.top), 
			w : objProgress.offsetWidth, 
			h : objProgress.offsetHeight 
		};
		try { __showHideElementsEx("SELECT", "HIDE", progressPos); } catch(E) {}
    }
}

/* 처리중 모달 close */
function progressWinClose()
{
	if( document.all.ProgressLayer1!=null){
    	progressLayerHidden();
		try { __showHideElementsEx("SELECT", "SHOW"); } catch(E) {}
    }
    
    /*
	if(newWin)
	{
	    newWin.close();
	    newWin = null;
	}
	*/
	top.focus();
}

/************************************************
* function : Layer 화면 가운데로 위치시키기 
* variable 
* -    strLayerid 레이어 아이디
*************************************************/
function comLayerPopCenter(strLayerid){
    var obj_centerlayer=document.getElementById(strLayerid);
    obj_centerlayer.style.display="";
    comLayerPopMove(strLayerid);                           
}   

function comLayerPopMove(strLayerid){                          
    var obj_centerLayer=document.getElementById(strLayerid);
    var bodywidth=document.documentElement.clientWidth;     
    var bodyheight=document.documentElement.clientHeight; 
        
    var divWidth=obj_centerLayer.offsetWidth;               
    var divHeight=obj_centerLayer.offsetHeight;             
    if(typeof document.body.style.maxHeight!="undefined"){  
            var bodyWidth=document.body.clientWidth;        
            var bodyHeight=document.body.clientHeight;          
            if(!!(window.attachEvent && !window.opera)){    
                    pageLeft=document.documentElement.scrollLeft;
                    pageTop=document.documentElement.scrollTop;  
            }else{                                               
                    pageLeft=window.pageXOffset;                 
                    pageTop=window.pageYOffset;                  
            }                                                    
    }else{                                                       
            pageLeft=document.documentElement.scrollLeft;        
            pageTop=document.documentElement.scrollTop;          
    }                                                            
    var divLeft=pageLeft,divTop=pageTop;                         
    if(bodyWidth > divWidth){                                    
            divLeft=pageLeft+Math.ceil((bodyWidth-divWidth)/2);  
    }                                                            
    if(bodyHeight > divHeight){                                  
            divTop=pageTop+Math.ceil((bodyHeight-divHeight)/2);  
    }                                                            
    obj_centerLayer.style.left=divLeft+"px";                     
    obj_centerLayer.style.top=divTop+"px";                       

    if(obj_centerLayer.style.display==""){                       
            setTimeout(function(){comLayerPopMove(strLayerid);},100);                                 
    }                                                                                                 
}  