Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"orderId"},
		        {name:"orderNo"},
		        {name:"productId"},
		        {name:"productNo"},
		        {name:"mainImgPath"},
		        {name:"productName"},
		        {name:"amount"},
		        {name:"unitPrice"},
		        {name:"basePrice"},
		        {name:"color"},
		        {name:"size"},
		        {name:"itemTotal"}
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
				items:[
						{
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
			                              {header:"id",width:50,dataIndex:"id"},
			                              {header:"orderId",width:50,dataIndex:"orderId"},
			                              {header:"订单号",width:150,dataIndex:"orderNo"},
			                              {header:"产品编号",width:120,dataIndex:"productNo"},
			                              {header:"产品名称",width:150,dataIndex:"productName"},
			                              {header:"折后产品单价",width:100,dataIndex:"unitPrice"},
			                              {header:"原产品单价",width:100,dataIndex:"basePrice"},
			                              {header:"件数",width:60,dataIndex:"amount"},
			                              {header:"总金额",width:100,dataIndex:"itemTotal"},
			                              {header:"颜色",width:100,dataIndex:"color"},
			                              {header:"尺寸",width:100,dataIndex:"size"}
			                              ])
	});
	
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch],
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
	
	function onSearch(){
		
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return ;
		}
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	
	
	
	
});

