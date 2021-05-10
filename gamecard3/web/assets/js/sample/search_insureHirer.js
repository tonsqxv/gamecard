<%@ page language="java" contentType="text/html; charset=utf-8"%>
Ext.onReady(function() {
			var gridStore = new Ext.data.JsonStore({
				url : 'caseHirer_listPageBy.action',
				fields : [ {name : 'caseId'}, 
				           {name : 'insureStatus'}, 
				           {name : 'empCode'}, 
				           {name : 'empName'}, 
				           {name : 'idcard'},
				           {name : 'personType'},
				           {name : 'dutyName'}, 
				           {name : 'deptId'},
				           {name : 'deptName'},
				           {name : 'divitionTm'}, 
				           {name : 'entryTime'}, 
				           {name : 'caseProcess'},
				           {name : 'caseSection'},
				           {name : 'casePoint'}, 
				           {name : 'caseType'},
				           {name : 'accidentType'} ,
				           {name : 'billFee'} , 
				           {name : 'medicalFee'} ,
				           {name : 'hospitalFee'} ,
				           {name : 'disablityFee'} ,
				           {name : 'deathFee'} , 
				           {name : 'incomeFee'} , 
				           {name : 'totalFee'} ,
				           {name : 'remark'}, 
				           {name : 'caseObject'}
				           
				],
				totalProperty : 'totalSize',
				root : 'root'
			});

			var dispalyDateFormat = new Ext.form.DateField({
				maxLength : 20,
				width : 120,
				disabled : false,
				format : "Y-m-d"
			});
			var dispalyDateFormat2 = new Ext.form.DateField({
				maxLength : 20,
				width : 120,
				disabled : false,
				format : "Y-m-d H:i:s"
			});
			
			function pretoday(days){
				var today = new Date() ;
				var newday_milliseconds = today.getTime()-1000*60*60*24*days ;
				var newday = new Date() ;
				newday.setTime(newday_milliseconds) ;
				return newday ;
			}
			
			var queryForm_atrr_1 = new Ext.form.DateField({width:125,name:"caseHirer.caseObject.reportTmBegin",format:"Y-m-d",value:pretoday(15),fieldLabel:"上报时间（开始）"});

			var queryForm_atrr_2 = new Ext.form.DateField({width:125,name:"caseHirer.caseObject.reportTmEnd",format:"Y-m-d",value:new Date(),fieldLabel:"上报时间（结束）"});

			var queryForm_atrr_3 = new Ext.form.ComboBox({hiddenName:"caseHirer.caseObject.caseLevel",store:new Ext.data.SimpleStore({data: [['','全部'],['1','一级'],['2','二级'],['3','三级'],['4','四级'],['5','五级']], fields: ['key','value']}),valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"案件级别",editable:false,mode:"local"});

			var queryForm_atrr_4 = new Ext.form.ComboBox({hiddenName:"caseHirer.caseObject.status",store:new Ext.data.SimpleStore({data: [['','全部'],['1','新增'],['2','上报'],['3','处理'],['4','完结'],['5','评估']], fields: ['key','value']}),valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"状态",editable:false,mode:"local"});

			var queryForm_atrr_5 = new Ext.form.TextField({width:125,name:"caseHirer.caseId",fieldLabel:"案件工单号"});
			
			var queryForm_atrr_6 = new Ext.form.TextField({width:125,name:"caseHirer.caseObject.eventId",fieldLabel:"事件工单号"});

			var selectDeptNode ;
			//选择所属部门窗口
			var selectDeptWin = new Ext.Window({
				width:600,
				height:400,
				frame:true,
				closeAction:'hide',
				layout:'fit',
				buttons: [
				          	{text:"确定",iconCls:'revert',handler:function(){
				          		if(selectDeptNode == null){
			          				Ext.MessageBox.alert("提示","请选择所属部门");
			          				return ;
			          			}
				          		//权限验证
				          		authorizedDepartment(selectDeptNode.id,function(){
				          			queryForm.getForm().findField("caseHirer.caseObject.reportDeptName").setValue(selectDeptNode.text);
				          			queryForm.getForm().findField("caseHirer.caseObject.reportDept").setValue(selectDeptNode.id);
				          			selectDeptWin.hide() ;
								
							 }.createDelegate(this));
				          	}},
							{text:"取消",iconCls:'close',handler:function() {selectDeptWin.hide();}
				}],
				items:[{
					xtype:'SF.ims.treePanel',
					listeners : {
						click : function(node) {
							selectDeptNode = node ;
							selectDeptWin.setTitle("选择上报地区/部门"+"<font color='red'> ->"+node.text+"</font>");
						}
					}		
				}
				]
			});
			var queryForm_atrr_7 = new Ext.form.TriggerField({
									width:125,
									name:"caseHirer.caseObject.reportDeptName",
									fieldLabel:"上报地区/部门",
									triggerClass : 'x-form-search-trigger',
									editable:false,
									enableKeyEvents :true,
									listerners:{
									},
									onTriggerClick:function(){
										selectDeptWin.setTitle("选择上报地区/部门");
										selectDeptWin.show() ;
									}
				
			});
			var queryForm_atrr_8 = new Ext.form.ComboBox({hiddenName:"caseHirer.isContainSubDept",store:new Ext.data.SimpleStore({data: [['1','包含'],['2','不包含']], fields: ['key','value']}),valueField:"key",displayField:"value",value:"1",width:125,triggerAction:"all",fieldLabel:"所辖网点",editable:false,mode:"local"});

			var queryForm_atrr_9 = new Ext.form.TextField({width:125,name:"caseHirer.empCode",fieldLabel:"工号"});
			
			var queryForm_atrr_10 = new Ext.form.TextField({width:125,name:"caseHirer.empName",fieldLabel:"姓名"});
			
			var queryForm_atrr_11 = new Ext.form.TextField({width:125,name:"caseHirer.idcard",fieldLabel:"身份证号"});
			
			var queryForm_atrr_12 = new Ext.form.ComboBox({hiddenName:"caseHirer.caseSection",store:new Ext.data.SimpleStore({data: [['','全部'],['1','6:00-14:00'],['2','14:01-22:00'],['3','22:01-5:59']], fields: ['key','value']}),valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"出险时间段",editable:false,mode:"local"});

			var queryForm_atrr_13 = new Ext.form.ComboBox({hiddenName:"caseHirer.accidentType",store:new Ext.data.SimpleStore({
				
				data: [['','全部'],['1','摔伤'],['2','扭伤'],['3','拉伤'],['4','划伤'],['5','压伤'],['6','砸伤'],['7','碰伤'],['8','撞伤'],['9','烫伤'],['10','烧伤'],['11','磕伤'],['12','咬伤'],['13','单方交通事故'],['14','双方交通事故'],['15','多方交通事故'],['16','残疾'],['17','死亡']], 
				fields: ['key','value']}),
				valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"事故类型",editable:false,mode:"local"
					});
			
			var queryForm_atrr_14 = new Ext.form.ComboBox({hiddenName:"caseHirer.casePoint",store:new Ext.data.SimpleStore({data: [['','全部'],['1','上班途中'],['2','下班途中'],['3','工作地点'],['4','家中'],['5','其它']], fields: ['key','value']}),valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"出险地点",editable:false,mode:"local"});
			var queryForm_atrr_15 = new Ext.form.Hidden({name:"caseHirer.caseObject.reportDept"});

			var queryForm = new Ext.form.FormPanel({
				frame : true,
				region : "north",
				autoHeight : true,
				fileUpload:true,
				items : [

				new Ext.form.FieldSet({
					labelWidth : 100,
					autoHeight:true,
					title : "查询条件",
					layout : 'column',
					items : [
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_1]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_2]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_3]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_4]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_5]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_6]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_7]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_8]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_9]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_10]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_11]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_12]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_13]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_14,queryForm_atrr_15]
							},{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [
									{
										text : "重置",
										xtype : "button",
										iconCls : 'revert',
										handler : function(){queryForm.getForm().reset() ;}
									}
								]
							}
							
					]
				}) ]
			});
			
			

			function checkboxRenderer(v, p, r) {
				var trueValue = eval(this.id + '.trueValue');
				p.css += ' x-grid3-check-col-td';
				return '<div class="x-grid3-check-col'
						+ ((v == trueValue) ? '-on' : '') + ' x-grid3-cc-'
						+ this.id + '">&#160;</div>';
			}

			// 日期显示格式
			var dispalyDateFormat = new Ext.form.DateField({
			maxLength:20,
			width:120,
			disabled:false,
			format:"Y-m-d"});
			function checkboxRenderer(v, p, r) {
			var trueValue = eval(this.id + '.trueValue');
			p.css += ' x-grid3-check-col-td';
			return '<div class="x-grid3-check-col'+((v==trueValue)?'-on':'')+' x-grid3-cc-'+this.id+'">&#160;</div>';
		}
			// 日期显示格式
			function datetimeRenderer(v, p, r) {
				if ((v == null) || (v.length < 19))
					return v;
				var dateFormat = eval(this.id + '.format');
				if (typeof (v) == "string") {
					v = v.replace('T', ' ');
					v = Date.parseDate(v, "Y-m-d H:i:s");
				}
				return v.format(dateFormat);
			}

		
			// 下拉框处理函数
			function comboboxRenderer(v, p, r) {
				if (v != null) {
					var records = eval(this.id
							+ '.initialConfig.store.getRange()');
					var keyField = eval(this.id + '.valueField');
					var displayField = eval(this.id + '.displayField');
					for ( var i = 0; i < records.length; i++) {
						var record = records[i];
						if (record.get(keyField) == v) {
							return record.get(displayField);
						}
					}
				}
				return v;
			}

			var pagingBar = new Ext.PagingToolbar(
					{
						displayInfo : true,
						displayMsg : "当前显示 {0} - {1} 总记录数目 {2}",
						store : gridStore,
						pageSize : pageSize,
						emptyMsg : "未检索到数据"
					});
			var checkboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
			var gridPanel = new Ext.grid.GridPanel({
				region : 'center',
				loadMask:true,
				tbar : pagingBar,
				store : gridStore,
				sm : checkboxSelectionMode,
				enableHdMenu : false,
				// stripeRows : true,
				cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),checkboxSelectionMode,
						{header : '案件工单号',width: 120,dataIndex : "caseId",	sortable : false},
						{header : '事件工单号',width: 120,dataIndex : "caseObject",renderer:eventIdRenderer, sortable : false} , 
						{header : '发生地点',width: 140,dataIndex : "caseObject",renderer:casePlaceRenderer,	sortable : false}, 
						{header : '案件类型',width: 100,dataIndex : "caseObject",renderer:insuretypeNameRenderer,sortable : false},
						{header : '状态',width: 80,dataIndex : "caseObject",renderer:statusRenderer,sortable : false}, 
						{header : '工号',width: 100,	dataIndex : "empCode",	sortable : false}, 
						{header : '姓名',width: 100,	dataIndex : "empName",	sortable : false}, 
						{header : '身份证号码',width: 140,dataIndex : "idcard",	sortable : false}, 
						{header : '用工形式',width: 100,	dataIndex : "personType",renderer:personTypeRenderer,	sortable : false}, 
						{header : '工作岗位',width: 100,dataIndex : "dutyName",sortable : false},  
						{header : '所属地区',width: 150,dataIndex : "deptName",sortable : false},  
						{header : '入职时间',width: 80,dataIndex : "divitionTm",renderer:divitionTmRenderer,sortable : false}, 
						{header : '入职时长（月）',width: 100,dataIndex : "entryTime",	sortable : false}, 
						{header : '出险时间段',width: 80,dataIndex : "caseSection",renderer:caseSectionRenderer,	sortable : false},
						{header : '出险地点',width: 80,dataIndex : "casePoint",renderer:casePointRenderer,sortable : false} , 
						{header : '出险类型',width: 80,dataIndex : "caseType",renderer:caseTypeRenderer,sortable : false},  
						{header : '事故类型',width: 80,dataIndex : "accidentType",renderer:accidentTypeRenderer,sortable : false},  
						{header : '出险过程',width: 120,dataIndex : "caseProcess",sortable : false},  
						{header : '票据金额',width: 80,dataIndex : "billFee",sortable : false},  
						{header : '医疗费',width: 80,dataIndex : "medicalFee",	sortable : false} ,
						{header : '住院津贴',width: 80,dataIndex : "hospitalFee",	sortable : false} ,
						{header : '伤残赔偿金',width: 80,dataIndex : "disablityFee",	sortable : false} ,
						{header : '身故赔偿金',width: 80,dataIndex : "deathFee",sortable : false} ,
						{header : '误工费',width: 80,dataIndex : "incomeFee",sortable : false} ,
						{header : '总赔付金额',width: 80,dataIndex : "totalFee",sortable : false} ,
						{header : '是否已投保',width: 80,dataIndex : "insureStatus",renderer:insureStatusRenderer,sortable : false} ,
						{header : '备注',width: 150,dataIndex : "remark",sortable : false} ,
						{header : '上报地区/部门',width: 150,dataIndex : "caseObject",renderer:reportDeptNameRenderer,sortable : false},
						{header : '上报时间',width: 120,dataIndex : "caseObject",renderer:reportTmRenderer,sortable : false} ,
						{header : '上报人',width: 80,dataIndex : "caseObject",renderer:reportUserNameRenderer,sortable : false} ,
						{header : '上报人联系方式',width: 100,dataIndex : "caseObject",renderer:reportContactRenderer,sortable : false} ,
						{header : '案件发现时间',width: 120,dataIndex : "caseObject",renderer:caseOccourTimeRenderer,sortable : false} ,
						{header : '案件发生时间',width: 120,dataIndex : "caseObject",renderer:caseFindTimeRenderer,sortable : false} ,
						{header : '案件级别',width: 60,dataIndex : "caseObject",renderer:caseLevelRenderer,sortable : false} ,
						{header : '案件描述',width: 150,dataIndex : "caseObject",renderer:caseDescRenderer,sortable : false} 
						]),
			listeners:{
				rowdblclick:onDetail
			}
			});
			function insureStatusRenderer(v){
				value = "" ;
				if(v == '0'){
					value = "未投保" ;
				}else if( v == '1'){
					value = "已投保" ;
				}
				return value ;
			}
			function accidentTypeRenderer(v){
				value = "" ;
				//1=摔伤2=扭伤3=拉伤4=划伤5=压伤6=砸伤7=碰伤8=撞伤9=烫伤10=烧伤11=磕伤12=咬伤13=单方交通事故14=双方交通事故15=多方交通事故16=残疾17=死亡
				if(v == '1'){
					value = "摔伤" ;
				}else if(v == '2'){
					value = "扭伤" ;
				}else if(v == '3'){
					value = "拉伤" ;
				}else if(v == '4'){
					value = "划伤" ;
				}else if(v == '5'){
					value = "压伤" ;
				}else if(v == '6'){
					value = "砸伤" ;
				}else if(v == '7'){
					value = "碰伤" ;
				}else if(v == '8'){
					value = "撞伤" ;
				}else if(v == '9'){
					value = "烫伤" ;
				}else if(v == '10'){
					value = "烧伤" ;
				}else if(v == '11'){
					value = "磕伤" ;
				}else if(v == '12'){
					value = "咬伤" ;
				}else if(v == '13'){
					value = "单方交通事故" ;
				}else if(v == '14'){
					value = "双方交通事故" ;
				}else if(v == '15'){
					value = "多方交通事故" ;
				}else if(v == '16'){
					value = "残疾" ;
				}else if(v == '17'){
					value = "死亡" ;
				}
				return value ;
			}
			function caseTypeRenderer(v){
				value = "" ;
				//1=工伤2=职业病
				if(v == '1'){
					value = "工伤" ;
				}else if(v == '2'){
					value = "职业病" ;
				}
				return value ;
			}
			function casePointRenderer(v){
				value = "" ;
				//1=上班途中2=下班途中3=工作地点4=家中5=其它
				if(v == '1'){
					value = "上班途中" ;
				}else if(v == '2'){
					value = "下班途中" ;
				}else if(v == '3'){
					value = "工作地点" ;
				}else if(v == '4'){
					value = "家中" ;
				}else if(v == '5'){
					value = "其它" ;
				}
				return value ;
			}
			function caseSectionRenderer(v){
				value = "" ;
				//1=(6：00-14：00)2=(14：01-22：00)3=(22：01-5：59)
				if(v == '1'){
					value = "6:00-14:00" ;
				}else if(v == '2'){
					value = "14:01-22:00" ;
				}else if(v == '3'){
					value = "22:01-5:59" ;
				}
				return value ;
			}
			function divitionTmRenderer(v){
				var value = v ;
				if(value != ''){
					value = value.split("T")[0] ;
				}
				return value ;
			}
			function personTypeRenderer(v){
				//1=全日制员工2=非全日制员工3=基地见习生4=劳务派遣5=实习生6=非员工
				var value="";
				if(v == '1'){
					value = "全日制员工" ;
				}else if(v = '2'){
					value = "非全日制员工" ;
				}else if(v = '3'){
					value = "基地见习生" ;
				}else if(v = '4'){
					value = "劳务派遣" ;
				}else if(v = '5'){
					value = "实习生" ;
				}else if(v = '6'){
					value = "非员工" ;
				}
				return value ;
			}
			function eventIdRenderer(v){
				return v.eventId ;
			}
			function casePlaceRenderer(v){
				return v.casePlace;
			}
			function insuretypeNameRenderer(v){
				return v.insuretypeName ;
			}
			function statusRenderer(v){
				var value="" ;
				if(v.status == '1'){
					value="新增" ;
				}else if(v.status == '2'){
					value="上报" ;
				}else if(v.status == '3'){
					value="处理" ;
				}else if(v.status == '4'){
					value="完结" ;
				}else if(v.status == '5'){
					value="评估" ;
				}
				return value ;
			}
			function reportDeptNameRenderer(v){
				return v.reportDeptName ;
			}
			function reportTmRenderer(v){
				var value = v.reportTm ;
				if(value != null){
					var time = value.split("T")[1] ;
					value = value.split("T")[0]+" "+time.split(":")[0]+":"+time.split(":")[0] ;
				}
				return value ;
			}
			function reportUserNameRenderer(v){
				return v.reportUserName ;
			}
			function reportContactRenderer(v){
				return v.reportContact ;
			}
			function caseOccourTimeRenderer(v){
				var value = v.caseOccourTime ;
				if(value != null){
					var time = value.split("T")[1] ;
					value = value.split("T")[0]+" "+time.split(":")[0]+":"+time.split(":")[0] ;
				}
				return value ;
			}
			function caseFindTimeRenderer(v){
				var value = v.caseFindTime ;
				if(value != null){
					var time = value.split("T")[1] ;
					value = value.split("T")[0]+" "+time.split(":")[0]+":"+time.split(":")[0] ;
				}
				return value ;
			}
			function caseLevelRenderer(v){
				var value ="" ;
				if(v.caseLevel == '1'){
					value = "一级";
				}else if(v.caseLevel == '2'){
					value = "二级";
				}else if(v.caseLevel == '3'){
					value = "三级";
				}else if(v.caseLevel == '4'){
					value = "四级";
				}else if(v.caseLevel == '5'){
					value = "五级";
				}
				return value ;
			}
			function caseDescRenderer(v){
				return v.caseDesc ;
			}
			new Ext.Viewport({
				layout : 'border',
				items : [
						{
							region : 'center',
							tbar : [ '-'
							        <app:isPermission code="/claim/searchInsureHirerSearch.action">
							        ,new Ext.Button({text:"查询",iconCls:'search',handler:onSearch })
							        </app:isPermission>
							        ,new Ext.Button({text:"查看",iconCls:'detail',handler:onDetail }) 
							        <app:isPermission code="/claim/searchInsureHirerExport.action">
							        ,new Ext.Button({text:"导出",iconCls:'revert',handler:onExport })
							        </app:isPermission>
							         ],
							layout : 'border',

							items : [ queryForm, gridPanel ]
						} ],
						listeners : {
							afterrender : function(view){
								onSearch() ;
							}
						}
			});

			// 查询
			function onSearch() {
				if (queryForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return ;
				} 
					
				gridPanel.getStore().baseParams = queryForm.getForm().getValues();
				gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
				gridPanel.getStore().load();
			}
			
			// 导出
			function onExport() {
				
					//导出前先进行规则验证
					Ext.Ajax.request({
						params:  queryForm.getForm().getValues(true) ,
						method :"POST",
						url: "caseHirer_exportValidate.action",
						success: function(response) {
							var resp = Ext.util.JSON.decode(response.responseText);
							if (resp.success) {
								queryForm.getForm().doAction('submit', {
									url : "exportCaseHirer.action",
									success : showExportResult,
									failure : showSaveOrUpdateFailureInfo
								});
							} else {
								Ext.MessageBox.alert('提示', resp.msg);
							}
						},
						failure:ajaxRequestFailure
					});
			}
			//导出成功处理事件
			  function showExportResult(form, action) {
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
			
			function showSaveOrUpdateFailureInfo(form, action) {
				var msg = '';
				if (action && action.result && action.result.error) {
					Ext.Msg.alert("提示","系统出现了异常,请与管理员联系!");
				} else {
					if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
						if (action.response.status == 0) {
							msg = "连接失败,无法连接到服务器!";
						} else if (action.response.status == -1) {
							msg = "服务器处理超时!";
						} else {
							msg = String.format("错误代码:{0}, 错误描述:{1}!",action.response.status,action.response.statusText);
						}
					} else if (action.failureType === Ext.form.Action.CLIENT_INVALID) {
						msg = "请将表单填写正确!";
					}
					Ext.Msg.alert("提示", msg);
				}
			}
			
			function onDetail() {
				operator_type = 'detail';
				var records = gridPanel.getSelectionModel().getSelections();
				var record = gridPanel.getSelectionModel().getSelected();
				if (records.length == 1) {
					 var url = "../claim/billSearch.action?sourceType=1&caseId="+record.data.caseId; ;
                     var title = "工单查询";
                     window.parent.refreshPage("billSearch", title,title, "", url, true);
					
				} else if (records.length < 1) {
					Ext.Msg.alert("提示","请选择一条记录");
				} else if (records.length > 1) {
					Ext.Msg.alert("提示","不能同时操作多条记录");
				}
			}
			
			
		});