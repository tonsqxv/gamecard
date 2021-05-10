<%@ page language="java" contentType="text/html; charset=utf-8"%>
Ext.onReady(function() {
	
	
	/***************query form*********************/
	var queryForm = new Ext.form.FormPanel({
		autoHeight : true,
		bodyStyle:"padding:0px,5px,0px,5px",
		items : [{
        	xtype:"fieldset",
			labelWidth : 100,
			autoHeight:true,
			title : "查询条件",
			layout : 'column',
			items : [{
				xtype:"panel",
				layout : 'form',
				columnWidth:.5,
				items:[{
		        	 xtype:"textfield",
		        	 disabled:false,
		        	 fieldLabel:"案件工单号",
		        	 width:125,
		        	 allowBlank:false ,
		        	 readOnly:setValueBySourceType('${sourceType}'),
		        	 name:"caseId",
		        	 value:"${caseId}"
		         }]
			},{
				xtype:"panel",
				layout : 'form',
				columnWidth:.3,
				items:[{
		        	 xtype:"button",
		        	 handler:onSearch,
		        	 disabled:setValueBySourceType('${sourceType}'),
		        	 iconCls:'search',
		        	 text:"查询"
		         }]
			}]
		}]
	});
	

	// 查询
	function onSearch() {
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return ;
		} 
		var caseId = queryForm.getForm().findField("caseId").getValue() ;	
		//校验案件工单号是否存在
		Ext.Ajax.request({params: {caseId: caseId},
			url: "bill_checkCaseExists.action",
			success: function(response) {
				var resp = Ext.util.JSON.decode(response.responseText);
				if (resp.success) {
					document.location.href="billSearch.action?sourceType=2&caseId="+caseId;
				} else {
					Ext.MessageBox.alert('提示', resp.msg);
				}
			},
			failure:ajaxRequestFailure
		});
		
		
	}
	
	// ajax请求失败处理事件
	function ajaxRequestFailure(response) {
		  if (response.status == 0) {
		      Ext.Msg.alert("提示", "无法连接到服务器，请检查网络是否正常");
		  } else if (response.status == -1) {
		      Ext.Msg.alert("提示", "服务器处理超时，请稍后再试");
		  } else {
				Ext.Msg.alert("提示","系统出现异常,请与管理员联系");
			}
		}
	
	function setValueBySourceType(v){
		if(v == '2'){
			return false ;
		}else if(v == '1'){
			return true ;
		}
		return false ;
	}
	
	/****************base form*******************/
	
	var baseFieldSet = new Ext.form.FieldSet({
		labelWidth : 100,
		autoHeight:true,
		title : "基础数据",
		layout : 'column',
		items : [
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
										xtype:"textfield",
										width:125,
										value:"${caseObject.caseId}",
										readOnly:true,
										fieldLabel:"案件工单号"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									value:"${caseObject.reportDeptName}",
									readOnly:true,
									fieldLabel:"上报地区/部门"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									value:"${caseObject.reportUserName}",
									readOnly:true,
									fieldLabel:"上报人"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									value:"${caseObject.reportContact}",
									readOnly:true,
									fieldLabel:"上报人联系方式"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									value:"${caseObject.insuretypeName}",
									readOnly:true,
									fieldLabel:"案件类型"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									name:"caseObject.caseOccourTime",
									value:"${caseObject.caseOccourTime}",
									readOnly:true,
									fieldLabel:"时间发生时间"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									name:"caseObject.caseFindTime",
									value:"${caseObject.caseFindTime}",
									readOnly:true,
									fieldLabel:"时间发现时间"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									value:"${caseObject.casePlace}",
									readOnly:true,
									fieldLabel:"发生地点"
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:125,
									name:"caseObject.reportCompanyTm",
									value:"${caseObject.reportCompanyTm}",
									readOnly:true,
									fieldLabel:"上报保险公司时间"
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textarea",
									height:80,
									width:360,
									value:"${caseObject.caseDesc}",
									readOnly:true,
									fieldLabel:"案件描述"
									}]
						}
		         ]
		});
	
	var leftBaseForm = new Ext.form.FormPanel({
		autoHeight : true,
		bodyStyle:"padding:0px,5px,0px,5px",
		items : [
		         baseFieldSet
		]
	});
	
	
	/***********************************/
	var dealGridStore = new Ext.data.JsonStore({
		autoLoad:true ,
		url : 'processDeal_listProcessDeals.action?processDeal.caseId=${caseId}',
		fields : [ {name : 'id'}, 
		           {name : 'caseId'}, 
		           {name : 'status'}, 
		           {name : 'isQuit'}, 
		           {name : 'quitPeason'},
		           {name : 'dealUser'},
		           {name : 'dealUserName'},
		           {name : 'dealDept'},
		           {name : 'dealContact'},
		           {name : 'dealTime'},
		           {name : 'dealIdea'},
		           {name : 'causeEvent'},
		           {name : 'payTime'},
		           {name : 'fullPay'},
		           {name : 'payReason'}
		           
		],
		root : 'root'
	});
	var checkboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
	var dealGridPanel = new Ext.grid.GridPanel({
		frame:true,
		loadMask:true,
		height:150,
		store : dealGridStore,
		sm : checkboxSelectionMode,
		enableHdMenu : false,
		cm : new Ext.grid.ColumnModel([// new Ext.grid.RowNumberer(),
				{header : '处理人',width: 50,dataIndex : "dealUser",	sortable : false},
				{header : '处理部门',width: 100,dataIndex : "dealDept",sortable : false} , 
				{header : '处理时间',width: 100,dataIndex : "dealTime",renderer:	dealTimeRenderer,sortable : false}, 
				{header : '节点',width: 200,dataIndex : "status",renderer:statusRenderer,sortable : false}
				]),
	listeners:{
		rowdblclick:showDealDetailWin
	}
	});
	
	function dealTimeRenderer(v){
		if(v!=null && v!= ''){
			v = v.split("T")[0] ; 
		}
		return v ;
	}
	
	function statusRenderer(v){
		var value = "" ;
		if(v == '1'){
			value = "<font color='red'>新增</font>-->上报-->处理-->完结-->评估" ;
		}else if(v == '2'){
			value = "新增--><font color='red'>上报</font>-->处理-->完结-->评估" ;
		}else if(v == '3'){
			value = "新增-->上报--><font color='red'>处理</font>-->完结-->评估" ;
		}else if(v == '4'){
			value = "新增-->上报-->处理--><font color='red'>完结</font>-->评估" ;
		}else if(v == '5'){
			value = "新增-->上报-->处理-->完结--><font color='red'>评估</font>" ;
		}
		return value ;
	}
	
	var dealDetailForm_atrr_1 = new Ext.form.TextField({width:150,name:"caseId",readOnly:true,fieldLabel:"案件工单号"});
	var dealDetailForm_atrr_2 = new Ext.form.ComboBox({hiddenName:"status",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','新增'],['2','上报'],['3','处理'],['4','完结'],['5','评估']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"状态",editable:false,mode:"local"});
	var dealDetailForm_atrr_3 = new Ext.form.TextField({width:150,name:"dealUserName",readOnly:true,fieldLabel:"处理人"});
	var dealDetailForm_atrr_4 = new Ext.form.TextField({width:150,name:"dealDept",readOnly:true,fieldLabel:"处理部门"});
	var dealDetailForm_atrr_5 = new Ext.form.DateField({width:150,name:"dealTime",readOnly:true,format:"Y-m-d",fieldLabel:"处理时间"});
	var dealDetailForm_atrr_6 = new Ext.form.TextField({width:150,name:"dealContact",readOnly:true,fieldLabel:"处理人联系方式"});
	var dealDetailForm_atrr_7 = new Ext.form.ComboBox({hiddenName:"causeEvent",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','未引发其它事件'],['2','劳动争议'],['3','法律诉讼'],['4','外部侵害'],['5','媒体事件'],['6','其它']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"引发其它事件",editable:false,mode:"local"});
	var dealDetailForm_atrr_8 = new Ext.form.DateField({width:150,name:"payTime",readOnly:true,format:"Y-m-d H:i",fieldLabel:"付款时间"});
	var dealDetailForm_atrr_9 = new Ext.form.ComboBox({hiddenName:"fullPay",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"是否全额赔付",editable:false,mode:"local"});
	var dealDetailForm_atrr_10= new Ext.form.ComboBox({hiddenName:"isQuit",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"是否放弃理赔",editable:false,mode:"local"});
	var dealDetailForm_atrr_11= new Ext.form.TextArea({height:80,width:440,name:"dealIdea",readOnly:true,fieldLabel:"进展情况描述"});
	var dealDetailForm_atrr_12= new Ext.form.TextArea({height:80,width:440,name:"quitPeason",readOnly:true,fieldLabel:"放弃理赔原因"});
	var dealDetailForm_atrr_13= new Ext.form.TextArea({height:80,width:440,name:"payReason",readOnly:true,fieldLabel:"未全额赔付原因"});
	
	var dealDetailForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,autoWidth:true,timeout:300
		,items: [
			new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_1]})
					,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_2]})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_3]})
					,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_4]})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_5]})
					,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_6]})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_7]})
					,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_8]})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_9]})
					,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_10]})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:550,layout:"form",items: [dealDetailForm_atrr_11]})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:550,layout:"form",items: [dealDetailForm_atrr_12]})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({width:550,layout:"form",items: [dealDetailForm_atrr_13]})
				]
			})
		]

	});
	
	// 处理记录详情
	var dealDetailWin = new Ext.Window({height:440,constrainHeader:true,width:630,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,items: [
		         dealDetailForm
		]
	});
	function showDealDetailWin(){
		var records = dealGridPanel.getSelectionModel().getSelections();
		var record = dealGridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
				dealDetailWin.setTitle("处理信息") ;
				dealDetailWin.show() ;
				dealDetailForm.getForm().reset() ;
				var obj = {};
				for (p in record.data) {
					if(p == 'dealTime'){
						if(record.data[p] != null ){
							obj[p] = record.data[p].split('T')[0] ;
						}else{
							obj[p] = '' ;
						}
						continue ;
					}
					if(p == 'payTime' ){
						if(record.data[p] != null ){
							var time = record.data[p].split("T")[1] ;
							obj[p] = record.data[p].split('T')[0]+" "+time.split(":")[0]+":"+time.split(":")[0] ;;
						}else{
							obj[p] = '' ;
						}
						continue ;
					}
					obj[p] = record.data[p]; 
				}
				dealDetailForm.getForm().setValues(obj);
		}
		
	}
	
	var dealFieldSet = new Ext.form.FieldSet({
		labelWidth : 100,
		autoHeight:true,
		title : "历史处理记录",
		layout : 'form',
		items : [
		         dealGridPanel	
		         ]
		})
	var leftDealForm = new Ext.form.FormPanel({
		autoHeight : true,
		bodyStyle:"padding:0px,5px,0px,5px",
		items : [
		         dealFieldSet
		]
	});
	/************************************/
	
	var serviceFieldSet = new Ext.form.FieldSet({
		labelWidth : 100,
		autoHeight:true,
		title : "服务评分",
		layout : 'column',
		items : [{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '1.收到报案时，保险经纪公司是否能够及时提供处理建议，便于索赔得以实现？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point1_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point1}",
										items: [
										        {boxLabel: "是", name: 'processAssess.point1', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point1', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '2.理赔资料收集时，保险经纪公司是否给予建议，便于案件顺利获得赔付？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point2_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point2}",
										items: [
										        {boxLabel: "是", name: 'processAssess.point2', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point2', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '3.理赔过程中，保险经纪公司是否能够及时告知案件进度？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point3_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point3}",
										items: [
										        {boxLabel: "是", name: 'processAssess.point3', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point3', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '4.对理赔有异议时，保险经纪公司是否给予合适的解释说明？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point4_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point4}",
										items: [
										        {boxLabel: "是", name: 'processAssess.point4', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point4', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '5.您对此次理赔结果是否满意？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point5_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point5}",
										items: [
										        {boxLabel: "是", name: 'processAssess.point5', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point5', inputValue: 2}
										    ],
									    listeners:{
									    	afterrender :function(o){
									    		if(o.getValue() != null){
									    			var inputValue = o.getValue().getRawValue() ;
										    		if(inputValue == 2){
										    			leftServiceForm.getForm().findField("processAssess.point5Desc").show();
										    		}else if(inputValue == 1){
										    			leftServiceForm.getForm().findField("processAssess.point5Desc").hide();
										    		}
									    		}
									    		
									    	}
									    }
										}
									]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "right",
							layout : "form",
							items : [{
									xtype:'textarea',
									height:80,
									width:300,
									hidden:true ,
									readOnly:true,
									name:"processAssess.point5Desc",
									value:"${processAssess.point5Desc}",
									fieldLabel:"原因说明"
									}]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '6.您对此次案件理赔的时效性是否满意？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point6}",
										items: [
										        {boxLabel: "是", name: 'processAssess.point6', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point6', inputValue: 2}
										    ]
										}
									]
						}
						,
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '7.保险经纪公司的服务态度如何？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point7_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point7}",
										items: [
										        {boxLabel: "礼貌、热情且专业", name: 'processAssess.point7', inputValue: 1},
										        {boxLabel: "不够专业", name: 'processAssess.point7', inputValue: 2},
										        {boxLabel: "不礼貌", name: 'processAssess.point7', inputValue: 3}
										    ],
									    listeners:{
									    	afterrender :function(o){
									    		if(o.getValue() != null){
									    			var inputValue = o.getValue().getRawValue() ;
										    		if(inputValue == 2 || inputValue == 3){
										    			leftServiceForm.getForm().findField("processAssess.point7Desc").show();
										    		}else if(inputValue == 1){
										    			leftServiceForm.getForm().findField("processAssess.point7Desc").hide();
										    		}
									    		}
									    		
									    	}
									    }
										}
									]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "right",
							layout : "form",
							items : [{
									xtype:'textarea',
									height:80,
									width:300,
									hidden:true ,
									readOnly:true,
									name:"processAssess.point7Desc",
									value:"${processAssess.point7Desc}",
									fieldLabel:"举例"
									}]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '8.请对此次保险经纪公司所提供的服务给予总体评价'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										disabled:true,
										value:"${processAssess.point8}",
										items: [
										        {boxLabel: "90-100", name: 'processAssess.point8', inputValue: 1},
										        {boxLabel: "80-90", name: 'processAssess.point8', inputValue: 2},
										        {boxLabel: "70-80", name: 'processAssess.point8', inputValue: 3},
										        {boxLabel: "60-70", name: 'processAssess.point8', inputValue: 4},
										        {boxLabel: "60以下", name: 'processAssess.point8', inputValue: 5}
										    ]
										}
									]
						}
		         ]
		})
	//服务评分
	var leftServiceForm = new Ext.form.FormPanel({
		autoHeight : true,
		border:true,
		bodyStyle:"padding:0px,5px,0px,5px",
		items : [
		         serviceFieldSet
		]
	});
	
	
	new Ext.Viewport({
		layout : 'fit',
		items:[{
			xtype:"panel",
			layout:"column",
			autoScroll:true,
			items : [{
				xtype:'panel', 
				columnWidth : .5,
				layout:"fit",
				height:1450,
				items:[{
					xtype:"panel",
					autoHeight:true,
					items:[{
				    	   xtype:"panel",
				    	   layout:"form",
				    	   autoHeight:true,
				    	   collapsible:true,
				    	   title:"工单查询",
				    	   frame:true,
				    	   items:[queryForm]
				       },{
				    	   xtype:"panel",
				    	   layout:"form",
				    	   autoHeight:true,
				    	   collapsible:true,
				    	   frame:true,
				    	   title:"基础信息",
				    	   items:[leftBaseForm]
				       },{
				    	   xtype:"panel",
				    	   layout:"form",
				    	   autoHeight:true,
				    	   collapsible:true,
				    	   frame:true,
				    	   title:"处理信息",
				    	   items:[leftDealForm]
				       },{
				    	   xtype:"panel",
				    	   autoHeight:true,
				    	   collapsible:true,
				    	   frame:true,
				    	   title:"评估信息",
				    	   items:[leftServiceForm]
				       }]
				}]
			},{
				xtype:'panel',
				id:"personalTplPanel",
				columnWidth : .5,
				items:[]
			}],
			listeners : {
				afterrender : function(view){
					var caseId = queryForm.getForm().findField("caseId").getValue() ;
					
					var personalTplPanel = Ext.getCmp("personalTplPanel");
					var personalForm ;
					var Object ;
					if(caseId == ''){//设置默认个性化模板
						personalForm = new SF.claim.BillEmpForm()
						personalTplPanel.removeAll();
						personalTplPanel.insert(0,personalForm);
						return ;
					}
					/*********格式化基础信息数据************/
					var caseOccourTime = leftBaseForm.getForm().findField("caseObject.caseOccourTime").getValue() ;
					var formate_caseOccourTime = caseOccourTime.split(" ")[0]+" "+caseOccourTime.split(" ")[1].split(":")[0]+":"+caseOccourTime.split(" ")[1].split(":")[1]
					leftBaseForm.getForm().findField("caseObject.caseOccourTime").setValue(formate_caseOccourTime) ;
					var caseFindTime = leftBaseForm.getForm().findField("caseObject.caseFindTime").getValue() ;
					var formate_caseFindTime = caseFindTime.split(" ")[0]+" "+caseFindTime.split(" ")[1].split(":")[0]+":"+caseFindTime.split(" ")[1].split(":")[1]
					leftBaseForm.getForm().findField("caseObject.caseFindTime").setValue(formate_caseFindTime);
					var reportCompanyTm = leftBaseForm.getForm().findField("caseObject.reportCompanyTm").getValue() ;
					if(reportCompanyTm != ''){
						var formate_reportCompanyTm = reportCompanyTm.split(" ")[0] ;
						leftBaseForm.getForm().findField("caseObject.reportCompanyTm").setValue(formate_reportCompanyTm);
					}
					/*********************/
					if('${caseObject.insuretypeId}' == '5'){//雇主责任险模板
						personalForm = new SF.claim.BillHirerForm();
					}else if('${caseObject.insuretypeId}' == '6'){//员工意外险模板
						personalForm = new SF.claim.BillEmpForm();
					}
					personalTplPanel.removeAll();
					personalTplPanel.insert(0,personalForm);
					
					//格式化个性化信息
					if('${caseObject.insuretypeId}' == '5'){//雇主责任险模板
						var divitionTm = personalForm.getForm().findField("caseHirer.divitionTm").getValue();
						if(divitionTm != ''){
							personalForm.getForm().findField("caseHirer.divitionTm").setValue(divitionTm.split(" ")[0]);
						}
					}else if('${caseObject.insuretypeId}' == '6'){//员工意外险模板
						var divitionTm = personalForm.getForm().findField("caseEmp.divitionTm").getValue();
						if(divitionTm != ''){
							personalForm.getForm().findField("caseEmp.divitionTm").setValue(divitionTm.split(" ")[0]);
						}
					}
					 
				}//end afterrender
			}
		}]
	});
	
	
			
});