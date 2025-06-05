 function ConsiderationFunc(jquery){
	 this.$ = jquery;
 }
 ConsiderationFunc.prototype.getStepStatusInfoPerCnsdreqid = function(cnsdreqId, func){
	this.$.ajax({
		url:"/clm/manage/consideration.do",
		data:{"method" : "getStepStatusinformationPerCnsdreqID", "menu_id":"20130319154642301_0000000355", "cnsdreq_id": cnsdreqId},
		dataType:"json"
	}).done(function(data){
		if(data.result){
			var returnData = {
				"step": data.prcs_depth_desc,
				"status": data.depth_status_desc,
				"status_depth": data.depth_status_index_desc
			};
			func(returnData);
		} else {
			alert("Step and Status information is not avaialble temporarily");
			return null;
		}
	});
};