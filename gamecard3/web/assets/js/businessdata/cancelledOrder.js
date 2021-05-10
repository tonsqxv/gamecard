Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list?orderStatus=5",
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
		        {name:"createTm"},
		        {name:"cancelledTm"}
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
 									name:"cancelledTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"取消时间（开始）"
 									}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"cancelledTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"取消时间（结束）"
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
			                              {header:"订单取消时间",width:150,renderer:datetimeRenderer,dataIndex:"cancelledTm"},
			                              {header:"订单总金额",dataIndex:"orderTotalPrice"},
			                              {header:"折后订单总金额",dataIndex:"discountTotalPrice"},
			                              {header:"优惠金额",dataIndex:"discountPrice"},
			                              {header:"订单生成时间",width:150,renderer:datetimeRenderer,dataIndex:"createTm"},
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
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnOrderDetail],
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
		           {name : 'unitPrice'}, 
		           {name : 'basePrice'}
		],
		root : 'root'
	});
	var orderDetailcheckboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
	var orderDetailGridPanel = new Ext.grid.GridPanel({
		frame:true,
		loadMask:true,
		height:300,
		store : orderDetailGridStore,
		sm : orderDetailcheckboxSelectionMode,
		enableHdMenu : false,
		cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
				{header : '订单号',width:150,dataIndex : "orderNo",	sortable : false},
				{header : '产品编号',width:125 ,dataIndex : "productNo",	sortable : false},
				{header : '产品名称',width:300 ,dataIndex : "productName",	sortable : false},
				{header : '折后订单单价',dataIndex : "unitPrice",	sortable : false},
				{header : '原订单价',dataIndex : "basePrice",	sortable : false},
				{header : '件数',dataIndex : "amount",	sortable : false}
				])
	});
	var orderDetailWin = new Ext.Window({
		autoHeight:true,
		constrainHeader : true,
		width : 1050,
		closeAction : "hide",
		resizable : false,
		isEditing : false,
		plain : true,
		modal : true,
		items :  [orderDetailGridPanel]
	});
	
	function onOrderDetail() {
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			orderDetailWin.setTitle("订单详情");
			orderDetailWin.show();
			orderDetailGridStore.baseParams["orderId"] = record.data.id;
			orderDetailGridStore.load() ;
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}

	}
	
	

	
	
});

