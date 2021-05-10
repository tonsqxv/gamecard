Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list?orderStatus=3",
		fields:[{name: "id"},
		        {name:"orderNo"},
		        {name:"orderTotalPrice"},
		        {name:"discountTotalPrice"},
		        {name:"discountPrice"},
		        {name:"zipCode"},
		        {name:"country"},
		        {name:"countryName"},
		        {name:"state"},
		        {name:"city"},
		        {name:"street1"},
		        {name:"street2"},
		        {name:"phoneNum"},
		        {name:"firstName"},
		        {name:"lastName"},
		        {name:"email"},
		        {name:"shippingCalculationMode"},
		        {name:"shippingOptionAmount"},
		        {name:"shippingOptionName"},
		        {name:"orderStatus"},
		        {name:"sessionId"},
		        {name:"memberId"},
		        {name:"memberName"},
		        {name:"transactionId"},
		        {name:"grossAmount"},
		        {name:"currencyId"},
		        {name:"createTm"},
		        {name:"payTm"},
		        {name:"shippingNo"},
		        {name:"dispatchTm"},
		        {name:"dispatchBee"},
		        {name:"dispatchKind"},
		        {name:"dispatchMailFlg"},
		        {name:"dispatchMailTm"},
		        {name:"addressLog"},
		        {name:"memberMessage"}
		        
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
		autoHeight:true,
		items: [

			new Ext.form.FieldSet({
				labelWidth:80,
				autoHeight:true,
				layout:"column",
				title:"查询条件",
				items:[{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:150,
									name:"orderNo",
									fieldLabel:"订单号"
							}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"dispatchTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"发货时间（开始）"
 									}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"dispatchTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"发货时间（结束）"
 									}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:150,
									name:"email",
									fieldLabel:"邮箱"
									}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"dispatchMailFlg",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['1','未邮件通知'],['2','已邮件通知']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"是否邮件通知",
									editable:false,
									mode:"local"
							      	}]
						}
				     ]
				})
	]});
	
	var pagingBar = new Ext.PagingToolbar({
		displayInfo:true,
		displayMsg:"当前显示 {0} - {1} 总记录数目 {2}",
		store:gridStore,
		pageSize:pageSize,
		emptyMsg:"未检索到数据"});
	var checkboxSelectionMode  = new Ext.grid.CheckboxSelectionModel({});
	var gridPanel = new Ext.grid.GridPanel(
			{
			region:"center",
			tbar:pagingBar,
			store:gridStore,
			sm: checkboxSelectionMode,
			//stripeRows : true,
			cm: new Ext.grid.ColumnModel([
			                              checkboxSelectionMode,
			                              {header:"orderid",width:80,dataIndex:"id"},
			                              {header:"订单号",width:150,dataIndex:"orderNo"},
			                              {header:"订单状态",renderer:orderStatusRenderer,dataIndex:"orderStatus"},
			                              {header:"邮件通知",width:100,renderer:dispatchMailFlgRenderer,dataIndex:"dispatchMailFlg"},
			                              {header:"邮件通知时间",width:150,renderer:datetimeRenderer,dataIndex:"dispatchMailTm"},
			                              {header:"发货物流编号",dataIndex:"shippingNo"},
			                              {header:"发货时间",width:150,renderer:datetimeRenderer,dataIndex:"dispatchTm"},
			                              {header:"发货费用",dataIndex:"dispatchBee"},
			                              {header:"发货物流",dataIndex:"dispatchKind"},
			                              {header:"订单总金额",dataIndex:"orderTotalPrice"},
			                              {header:"折后订单总金额",dataIndex:"discountTotalPrice"},
			                              {header:"优惠金额",dataIndex:"discountPrice"},
			                              {header:"paypal交易号",dataIndex:"transactionId"},
			                              {header:"paypal交易金额",dataIndex:"grossAmount"},
			                              {header:"交易货币种类",dataIndex:"currencyId"},
			                              {header:"订单生成时间",width:150,renderer:datetimeRenderer,dataIndex:"createTm"},
			                              {header:"paypal付款时间",width:150,renderer:datetimeRenderer,dataIndex:"payTm"},
			                              {header:"物流运费",dataIndex:"shippingOptionAmount"},
			                              {header:"用户选择的物流",dataIndex:"shippingOptionName"},
			                              {header:"zip/code",dataIndex:"zipCode"},
			                              {header:"国家代码",dataIndex:"country"},
			                              {header:"国家",dataIndex:"countryName"},
			                              {header:"省/市",dataIndex:"state"},
			                              {header:"城市",dataIndex:"city"},
			                              {header:"地址1",dataIndex:"street1"},
			                              {header:"地址2",dataIndex:"street2"},
			                              {header:"联系方式",dataIndex:"phoneNum"},
			                              {header:"姓",dataIndex:"firstName"},
			                              {header:"名",dataIndex:"lastName"},
			                              {header:"邮箱",dataIndex:"email"},
			                              {header:"系统默认物流（供参考）",width:150,dataIndex:"shippingCalculationMode"},
			                              {header:"sessionid",dataIndex:"sessionId"},
			                              {header:"会员",width:200,dataIndex:"memberName"}
			                              ])
	});
	function datetimeRenderer(v){
		var value = v ;
		if(v != null && v != ''){
			value = formatDate(new Date(v.time)) ;
		}
		return value ;
	}
	function dispatchMailFlgRenderer(v){
		var value ="" ;
		if(v == '1'){
			value = '<font color=\'red\'>未邮件通知</font>' ;
		}else if(v == '2'){
			value = '已邮件通知' ;
		}
		return value ;
	}
	function orderStatusRenderer(v){
		var value ="" ;
		if(v == '1'){
			value = '<font color=\'red\'>未付款</font>' ;
		}else if(v == '2'){
			value = '已付款' ;
		}else if(v == '3'){
			value = '已发货' ;
		}else if(v == '4'){
			value = '已完结' ;
		}else if(v == '5'){
			value = '取消' ;
		}else if(v == '6'){
			value = '申请退款' ;
		}else if(v == '7'){
			value = '已退款' ;
		}else if(v == '8'){
			value = '拒绝退款' ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnOrderDetail = new Ext.Button({text:"查看订单详情",iconCls:'detail',handler:onOrderDetail }) ;
	var btnShpping = new Ext.Button({text:"修改发货物流",iconCls:'edit',handler:onShpping }) ;
	var btnOrderCompleted = new Ext.Button({text:"关闭交易",iconCls:'apply',handler:onOrderCompleted }) ;
	var btnSendMail = new Ext.Button({text:"邮件通知",iconCls:'apply',handler:onSendMail }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnOrderDetail,"-",btnShpping,"-",btnSendMail,"-",btnOrderCompleted],
			layout:"border",
			//bodyBorder:false,
			items:[
			       queryForm,
			       gridPanel
			       ]
		}],
		listeners : {
			afterrender : function(view){
				onSearch() ;
			}
		}
	});
	var operator_type ;
	function onSearch(){
		
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return ;
		}
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	/******************完成********************/
	function onOrderCompleted(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			Ext.MessageBox.confirm('提示','请确认客户已收货，再关闭交易，是否确认？', function(result){
				if (result == 'yes') {
					Ext.Ajax.request({params: {id: record.data.id},
						url: "completed",
						success: function(response) {
							var resp = Ext.util.JSON.decode(response.responseText);
							if (resp.success) {
								Ext.MessageBox.alert('提示','操作成功');
							} else {
								Ext.MessageBox.alert('操作失败', resp.msg);
							}
							gridPanel.getStore().load();
						},
						failure:ajaxRequestFailure
					});
				}
				
			});
		}else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}else if (records.length < 1) {
			Ext.MessageBox.alert('提示', '选择需要退款的订单');
		}  
	}
	
	/**********************查看订单详情*************************/
	var orderDetailGridStore = new Ext.data.JsonStore({
		//autoLoad:true ,
		url : '../orderDetail/listOrderDetailsByOrderId',
		fields : [ {name : 'id'}, 
		           {name : 'orderId'}, 
		           {name : 'orderNo'}, 
		           {name : 'productId'}, 
		           {name : 'productNo'}, 
		           {name : 'productName'}, 
		           {name : 'amount'}, 
		           {name : 'size'}, 
		           {name : 'color'}, 
		           {name : 'unitPrice'}, 
		           {name : 'basePrice'}
		],
		root : 'root'
	});
	var orderDetailcheckboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
	var orderDetailGridPanel = new Ext.grid.GridPanel({
		frame:true,
		loadMask:true,
		height:200,
		store : orderDetailGridStore,
		sm : orderDetailcheckboxSelectionMode,
		enableHdMenu : false,
		cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
				{header : '订单号',width:150,dataIndex : "orderNo",	sortable : false},
				{header : '产品编号',width:125 ,dataIndex : "productNo",	sortable : false},
				{header : '产品名称',width:300 ,dataIndex : "productName",	sortable : false},
				{header : '折后订单单价',dataIndex : "unitPrice",	sortable : false},
				{header : '原订单价',dataIndex : "basePrice",	sortable : false},
				{header : '件数',dataIndex : "amount",	sortable : false},
				{header : '颜色',dataIndex : "color",	sortable : false},
				{header : '尺寸',dataIndex : "size",	sortable : false}
				])
	});
	var detailForm = new Ext.form.FormPanel({
		frame:true,
		autoHeight:true,
		items: [
			new Ext.form.FieldSet({
				labelWidth:80,
				autoHeight:true,
				layout:"column",
				title:"地址信息",
				items:[{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:800,
									name:"address",
									fieldLabel:"买家收货地址"
							}]
						},{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:800,
									name:"addressLog",
									fieldLabel:"paypal填写的收货地址"
							}]
						},{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textarea",
									height:80,
									width:800,
									name:"memberMessage",
									fieldLabel:"买家留言信息"
							}]
						}]
				})
	]});
	var orderDetailWin = new Ext.Window({
		autoHeight:true,
		constrainHeader : true,
		width : 1050,
		closeAction : "hide",
		resizable : false,
		isEditing : false,
		plain : true,
		modal : true,
		items :  [detailForm,orderDetailGridPanel]
	});
	
	function onOrderDetail() {
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			orderDetailWin.setTitle("订单详情");
			orderDetailWin.show();
			orderDetailGridStore.baseParams["orderId"] = record.data.id;
			orderDetailGridStore.load() ;

			var street2  =record.data.street2 ;
			if(street2 == null || street2 == ''){
				street2 = "" ;
			}else{
				street2 = ","+street2+" " ;
			}
			var address = record.data.lastName+" "+record.data.firstName+" "+record.data.street1+" "+street2+record.data.city+", "+record.data.state+" "+record.data.zipCode+" "+record.data.countryName ;
			detailForm.getForm().findField("address").setValue(address) ;
			detailForm.getForm().findField("memberMessage").setValue(record.data.memberMessage) ;
			detailForm.getForm().findField("addressLog").setValue(record.data.lastName+" "+record.data.firstName+" "+record.data.addressLog) ;
		
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}

	}
	
	/**********************邮件通知*******************************/
	function onSendMail(){
		var records = gridPanel.getSelectionModel().getSelections();
		if (records.length < 1) {
			Ext.MessageBox.alert('提示', '请选择需要操作的数据');
		} else {
			Ext.MessageBox.confirm('提示','确定要执行邮件通知操作', comfirmSendMail);
		}
	}
	function comfirmSendMail(result){
		if (result == 'yes') {
			var records = gridPanel.getSelectionModel().getSelections();
			var ids = '';
			for(var i = 0; i < records.length; i++) {
				if(i==0){
					ids += records[i].data.id ;
				}else{
					ids += ',' + records[i].data.id ;
				}
			}
			Ext.Ajax.request({params: {ids: ids},
				url: "dispatchSendMail",
				success: function(response) {
					var resp = Ext.util.JSON.decode(response.responseText);
					if (resp.success) {
						Ext.MessageBox.alert('提示','邮件通知成功');
					} else {
						Ext.MessageBox.alert('操作失败', resp.msg);
					}
					gridPanel.getStore().load();
				},
				failure:ajaxRequestFailure
			});
		}
	}
	
	/*****************修改发货物流**************************/
	var dispatchForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [
			new Ext.Panel({layout:"column",
				items: [{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{	
									xtype:"textfield",
									width:300,
									name:"shippingNo",
									allowBlank:false,
									fieldLabel:"物流单号"}
									,{
										xtype:"hidden",
										name:"id"
									}]
						},{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{	
									xtype:"textfield",
									name:"dispatchKind",
									width:300,
									allowBlank:false,
									fieldLabel:"物流"}]
						},{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 100,
							labelAlign : "left",
							layout : "form",
							items : [{	
									xtype:"numberfield",
									width:300,
									name:"dispatchBee",
									decimalPrecision:2,
									minValue:0,
									allowBlank:false,
									fieldLabel:"物流费用（RMB）"}]
						}]
			})
		]
	});
	var dispatchWin = new Ext.Window({height:200,constrainHeader:true,width:500,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        new Ext.Button({text:"保存",iconCls:'save',handler:updateShppingSave})
		]
		,items: [
		         dispatchForm
		]
	});
	function updateShppingSave(){
		if (dispatchForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		
		dispatchForm.getForm().submit({
					url:"updateShppingInfo",
					success : function(form, action){
						if (action.result.msg) {
							Ext.Msg.alert("提示",action.result.msg);
						} else {
								Ext.Msg.alert("提示","操作成功");
								dispatchWin.hide();
								gridStore.reload();
						}
					},
					failure : showFormSubmitFailureInfo,
					waitMsg : "正在执行...",
					waitTitle : "请稍后..."
				});
		
		
	}
	
	function onShpping(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			dispatchWin.setTitle("修改发货物流信息");
			dispatchWin.show() ;
			dispatchForm.getForm().reset() ;
			var obj = {};
			for (p in record.data) {
				obj[p] = record.data[p]; 
			}
			dispatchForm.getForm().setValues(obj);
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
	}
});

