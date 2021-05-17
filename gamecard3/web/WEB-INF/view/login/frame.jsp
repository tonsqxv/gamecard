<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%
String path = request.getContextPath();    
//获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是 http://localhost:8080/MyApp/）:    
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>CMS管理系统-生产环境-1.0</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="<%=basePath %>/assets/ext-3.4.0/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=basePath %>/assets/ext-3.4.0/adapter/ext/ext-base.js"></script>

<script type="text/javascript" src="<%=basePath %>/assets/ext-3.4.0/ext-all.js"></script>
<script type="text/javascript"
	src="<%=basePath %>/assets/ext-3.4.0/src/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
		Ext.BLANK_IMAGE_URL = '<%=basePath %>/assets/ext-3.4.0/resources/images/default/s.gif' ;
	</script>

</head>

<body onload="if(document.images) new Image().src= Ext.BLANK_IMAGE_URL;"
	onclick="if(window.self != window.top &&window.top&&window.top.Ext) window.top.Ext.menu.MenuMgr.hideAll();">
<script type="text/javascript">
		Ext.onReady(function(){
			Ext.QuickTips.init();
			Ext.state.Manager.setProvider(new Ext.state.CookieProvider({
				expires:null
				}));
			});

		var menu_user = {id:"user",text:"用户管理",handler:function(item){openMenu(item, '<%=basePath%>/user/main', '用户管理', '');}};

		var menu_role = {id:"role",text:"角色管理",handler:function(item){openMenu(item, '<%=basePath%>/role/main', '角色管理', '');}};

		var menu_config = {id:"config",text:"系统参数配置",handler:function(item){openMenu(item, '<%=basePath%>/config/main', '系统参数配置', '');}};

		var menu_sysManager = {
									id:"sysManager",
									text:"系统管理",
									handler:function(item){
											return;openMenu(item, '..', '系统管理', '');
											}
					
									,menu: new Ext.menu.Menu({
												items: [
														menu_user
														,menu_role
														,menu_config
														]
												})
								};

		var menu_category = {id:"category",text:"类别管理",handler:function(item){openMenu(item, '<%=basePath%>/category/main', '类别管理', '');}};

		var menu_brand = {id:"brand",text:"品牌管理",handler:function(item){openMenu(item, '<%=basePath%>/brand/main', '品牌管理', '');}};

		var menu_baseProduct = {id:"baseProduct",text:"产品管理",handler:function(item){openMenu(item, '<%=basePath%>/baseProduct/main', '产品管理', '');}};
		
		var menu_shippingOption = {id:"shippingOption",text:"物流管理",handler:function(item){openMenu(item, '<%=basePath%>/shippingOption/main', '物流管理', '');}};
		
		var menu_country = {id:"country",text:"国家代码管理",handler:function(item){openMenu(item, '<%=basePath%>/country/main', '国家代码管理', '');}};

		var menu_dictionary = {id:"dictionary",text:"数据字典管理",handler:function(item){openMenu(item, '<%=basePath%>/dictionary/main', '数据字典管理', '');}};
		
		var menu_baseData = {
							id:"baseData",
							text:"基础数据",
							handler:function(item){
												return;openMenu(item, '..', '基础数据', '');
												}

							,menu: new Ext.menu.Menu({
								items: [
											menu_category
											,menu_brand
											,menu_baseProduct
											,menu_shippingOption
											,menu_country
											,menu_dictionary
										]
							})
						};
		
		var menu_member = {id:"member",text:"会员管理",handler:function(item){openMenu(item, '<%=basePath%>/member/main', '会员管理', '');}};
		
		var menu_guest = {id:"guest",text:"游客管理",handler:function(item){openMenu(item, '<%=basePath%>/guest/main', '游客管理', '');}};

		var menu_contact = {id:"contact",text:"留言信息管理",handler:function(item){openMenu(item, '<%=basePath%>/contact/main', '留言信息管理', '');}};
	
		var menu_task = {id:"task",text:"定时器管理",handler:function(item){openMenu(item, '<%=basePath%>/task/main', '定时器管理', '');}};
		
		var menu_taskEmail = {id:"taskEmail",text:"目标客户",handler:function(item){openMenu(item, '<%=basePath%>/taskEmail/main', '目标客户', '');}};
		
		var menu_bizData = {
							id:"bizData",
							text:"业务数据",
							handler:function(item){
												return;openMenu(item, '..', '业务数据', '');
												}

							,menu: new Ext.menu.Menu({
								items: [
											menu_member
											,menu_guest
											,menu_contact
											,menu_task
											,menu_taskEmail
										]
							})
						};
		
		var menu_order = {id:"order",text:"订单查询",handler:function(item){openMenu(item, '<%=basePath%>/order/main', '订单管理', '');}};
		
		var menu_orderDetail = {id:"orderDetail",text:"订单详情查询",handler:function(item){openMenu(item, '<%=basePath%>/orderDetail/main', '订单详情管理', '');}};
		
		var menu_shopItem = {id:"shopItem",text:"购物车管理",handler:function(item){openMenu(item, '<%=basePath%>/shopItem/main', '购物车管理', '');}};
		
		var menu_awaitingPayOrder = {id:"awaitingPayOrder",text:"未付款订单",handler:function(item){openMenu(item, '<%=basePath%>/order/awaitingPayOrder', '未付款订单', '');}};
		
		var menu_prepareSendOrder = {id:"prepareSendOrder",text:"待发货订单",handler:function(item){openMenu(item, '<%=basePath%>/order/prepareSendOrder', '待发货订单', '');}};
		
		var menu_sendOrder = {id:"sendOrder",text:"已发货订单",handler:function(item){openMenu(item, '<%=basePath%>/order/sendOrder', '已发货订单', '');}};
		
		var menu_completedOrder = {id:"completedOrder",text:"已完成订单",handler:function(item){openMenu(item, '<%=basePath%>/order/completedOrder', '已完成订单', '');}};
		
		var menu_cancelledOrder = {id:"cancelledOrder",text:"已取消订单",handler:function(item){openMenu(item, '<%=basePath%>/order/cancelledOrder', '已取消订单', '');}};
		
		var menu_refundApplyOrder = {id:"refundApplyOrder",text:"申请退款订单",handler:function(item){openMenu(item, '<%=basePath%>/order/refundApplyOrder', '申请退款订单', '');}};
		
		var menu_auditedOrder = {id:"auditedOrder",text:"退款审核通过订单",handler:function(item){openMenu(item, '<%=basePath%>/order/auditedOrder', '退款审核通过订单', '');}};
		
		var menu_refundedOrder = {id:"refundedOrder",text:"已退款订单",handler:function(item){openMenu(item, '<%=basePath%>/order/refundedOrder', '已退款订单', '');}};

		var menu_declinedOrder = {id:"declinedOrder",text:"拒绝退款订单",handler:function(item){openMenu(item, '<%=basePath%>/order/declinedOrder', '拒绝退款订单', '');}};

		var menu_orderManager = {
				id:"orderManager",
				text:"订单管理",
				handler:function(item){
									return;openMenu(item, '..', '订单管理', '');
									}

				,menu: new Ext.menu.Menu({
					items: [
							menu_order
							,menu_orderDetail
							,menu_shopItem
							,menu_awaitingPayOrder
							,menu_prepareSendOrder
							,menu_sendOrder
							,menu_completedOrder
							,menu_cancelledOrder
							,menu_refundApplyOrder
							,menu_auditedOrder
							,menu_refundedOrder
							,menu_declinedOrder
							]
				})
			};

		//var menu_people2 = {id:"people2",text:"人员管理",handler:function(item){openMenu(item, '../../sys/emp_forward.action', '人员管理', '');}};

		var menu_reportData = {
							id:"reportData",
							text:"报表管理",
							handler:function(item){
												return;openMenu(item, '..', '报表管理', '');
												}

							,menu: new Ext.menu.Menu({
								items: [
											//menu_people2
										]
							})
						};
		
		

		var menuBar = new Ext.Toolbar({
							enableOverflow:"true"
							,items: [
										'-'	,menu_sysManager,'-',menu_baseData,'-',menu_bizData,'-',menu_orderManager,'-',menu_reportData,'-','->'
										,{
											icon:"<%=basePath %>/assets/images/frame/new.gif",
											text:"工具",
											cls:"x-btn-text-icon"
											,menu: new Ext.menu.Menu({
													showSeparator:"false"
													,items: [
															{
																icon:"<%=basePath %>/assets/images/frame/home.gif",
																text:"返回首页",
																cls:"x-btn-text-icon",
																handler:function(){
																		tabs.setActiveTab('home');
																		}
															}
															,{
																icon:"<%=basePath %>/assets/images/frame/close.gif",
																text:"关闭所有标签页",
																cls:"x-btn-text-icon",
																handler:function(){
																		closeAllTab();
																		}
															}
															,{
																icon:"<%=basePath %>/assets/images/frame/version.gif",
																text:"版本信息",
																cls:"x-btn-text-icon",
																handler:function(){
																		versionWin.show();
																		}
															}
															,{
																icon:"<%=basePath %>/assets/images/frame/user.gif",
																text:"用户信息",
																cls:"x-btn-text-icon",
																handler:function(){
																		userWin.show();
																		}
															}
															,{
																icon:"<%=basePath %>/assets/images/frame/password.gif",
																text:"修改密码",
																cls:"x-btn-text-icon",
																handler:function(){
																		changePasswordWin.show();
																		}
															}
															,{
																icon:"<%=basePath %>/assets/images/frame/exit.gif",
																text:"退出登录",
																cls:"x-btn-text-icon",
																handler:function(){
																		logout();
																		}
															}
															]
												})
											}
											,
											' '
											]
										});

		var tabs = new Ext.TabPanel({
										region:"center",
										autoScroll:true,
										activeItem:"home",
										border:false,
										enableTabScroll:true
										,items: [
												new Ext.Panel({id:"home",title:"首页",layout:"border"
																,items: [
																		new Ext.Panel({id:"homemain",region:"center",border:false
																						,html: "\r\n\t\t\t\t\t\t\t\t\t\t\t<iframe src=\'<%=basePath%>/home\' width=\'100%\' height=\'100%\' frameborder=\'0\'></iframe>\r\n\t\t\t\t\t\t\t\t\t\t"
				
																						})
																		,new Ext.Panel({id:"homebar",region:"north",cls:"x-toolbar",height:25,layout:"border",border:false
				
																				,items: [
																						new Ext.Panel({id:"homemsg",region:"center",border:false
																										,html: "\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<table border=\'0\' cellpadding=\'0\' cellspacing=\'0\' width=\'100%\' height=\"25\" style=\'background-color: #FFDF8C;\'><tr><td width=\'24\' align=\'center\'><img src=\'<%=basePath %>/assets/images/frame/info.gif\' border=\'0\' height=\'16\' width=\'16\'/></td><td>XX管理系统demo</td></tr></table>\r\n\t\t\t\t\t\t\t\t\t\t\t\t"
																									})
																						,new Ext.Panel({id:"homebtn",region:"east",width:22,border:false
																								,items: [
																										new Ext.Button({
																														id:"homehelp",icon:"<%=basePath %>/assets/images/frame/help.gif",cls:"x-btn-icon",style:"background-color: #FFDF8C;",tooltip:"帮助",handler:function() {showHelp('');}
																														})
																										]
																											})
				
																						]
																					})
																			]
															})
													]
								});

		var framePane = new Ext.Viewport({
							layout:"border"
							,items: [
									new Ext.Panel({
													tbar:menuBar,
													region:"center",
													layout:"border",
													border:false
													,items: [
															tabs
															]
												})
									]
						});

		var userWin = new Ext.Window({
									title:"用户信息",
									height:200,
									frame:true,
									width:300,
									layout:"border",
									closeAction:"hide",
									modal:true
									,items: [
											new Ext.Panel({
															region:"center",
															frame:true,
															border:false
															,html:"\r\n\t\t\t\t\t<table border=\"0\" width=\"100%\" class=\"x-form-field\" style=\"font-size:15\">"+
																"\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td colspan=\"2\">&nbsp;</td>\r\n\t\t\t\t\t\t</tr>"+
																"\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td style=\"border-bottom:1px dashed #BEBEBE\" align=\"right\" width=\"120\">用户名&nbsp;:&nbsp;&nbsp;</td>"+
																"\r\n\t\t\t\t\t\t\t<td style=\"border-bottom:1px dashed #BEBEBE\">${user.userName}</td>"+
																"\r\n\t\t\t\t\t\t\r\n\t\t\t\t\t</table>\r\n\t\t\t\t"
														})
											]
									});

		var versionWin = new Ext.Window({
									title:"版本信息",
									height:330,
									frame:true,
									width:345,
									layout:"border",
									closeAction:"hide",
									modal:true
									,items: [
											new Ext.Panel({
												region:"center",
												frame:true,
												border:false
												,html:"\r\n\t\t\t\t\t<table border=\"0\" class=\"x-form-field\" style=\"font-size:14\">\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td colspan=\"2\"><a href=\"http://www.sf-express.com\" target=\"_blank\"><img style=\"background-color: black\" src=\"<%=basePath %>/assets/images/frame/sf_logo.png\" alt=\"SF-Express\"/></a></td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td colspan=\"2\" style=\"font-size:20\">XX管理系统demo</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td colspan=\"2\"><hr/></td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"right\" width=\"120\">版本类型&nbsp;:&nbsp;&nbsp;</td>\r\n\t\t\t\t\t\t\t<td>生产环境(product)</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"right\">版本号&nbsp;:&nbsp;&nbsp;</td>\r\n\t\t\t\t\t\t\t<td>1.0</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"right\">发布时间&nbsp;:&nbsp;&nbsp;</td>\r\n\t\t\t\t\t\t\t<td>@release.date@</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"right\">Framework&nbsp;:&nbsp;&nbsp;</td>\r\n\t\t\t\t\t\t\t<td>EXT3-MIXROLE</td>\r\n\t\t\t\t\t\t</tr>\t\t\t\t\t\t\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td colspan=\"2\"><hr/></td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td colspan=\"2\">Copyright (c) 2012, S.F. Express Inc. All rights reserved.</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td colspan=\"2\"><br/></td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t"
												})
											]
									});

		var oldPasswordVar = new Ext.form.TextField({minLengthText:"旧密码最小长度为8",allowBlank:false,maxLength:20,blankText:"旧密码不能为空",minLength:6,inputType:"password",name:"oldPassword",maxLengthText:"旧密码最大长度为20",fieldLabel:"旧密码"});

		var newPasswordVar = new Ext.form.TextField({minLengthText:"新密码最小长度为8",allowBlank:false,maxLength:20,blankText:"新密码不能为空",minLength:6,inputType:"password",name:"newPassword",maxLengthText:"新密码最大长度为20",fieldLabel:"新密码"});

		var newPasswordConfirmVar = new Ext.form.TextField({minLengthText:"确认新密码最小长度为8",allowBlank:false,maxLength:20,blankText:"确认新密码不能为空",minLength:6,inputType:"password",name:"newPasswordConfirm",maxLengthText:"确认新密码最大长度为20",fieldLabel:"确认新密码"});

		var changePasswordForm = new Ext.form.FormPanel({
										region:"center",
										frame:true,
										border:false
										,items: [
												oldPasswordVar
												,newPasswordVar
												,newPasswordConfirmVar
												]
									});

		var changePasswordWin = new Ext.Window({
									title:"修改密码",
									height:240,
									frame:true,
									width:300,
									layout:"border",
									closeAction:"hide",
									modal:true
									,items: [
											new Ext.Panel({
														region:"center",
														layout:"border",
														border:false
														,tbar: [
																new Ext.Button({text:"保存",cls:"x-btn-normal",handler:saveChangePwd	})
																,new Ext.Button({text:"重置",cls:"x-btn-normal",handler:function() {changePasswordForm.getForm().reset();}	})
																]
														,items: [
																	changePasswordForm
																]
														})
											]
									});
		changePasswordForm.savePassword = function() {
											changePasswordForm.getForm().doAction(
																		'submit',
																		{
																			failure:changePasswordFailure,
																			success:changePasswordSuccess,
																			url:"<%=basePath %>/user/changPassword"});
																		}


		    function saveChangePwd() {
						if(changePasswordForm.getForm().isValid()) {
							var oldPasswordVarValue = oldPasswordVar.getValue();
							var newPasswordVarValue = newPasswordVar.getValue();
							var newPasswordConfirmVarValue = newPasswordConfirmVar.getValue();
							if (validatePassword(oldPasswordVarValue, newPasswordVarValue, newPasswordConfirmVarValue))
								changePasswordForm.savePassword();
						}
		    }



			// 检验密码表单数据

			function validatePassword(oldPasswordVarValue, newPasswordVarValue, newPasswordConfirmVarValue) {
				if(! checkPassword(oldPasswordVarValue, '旧密码')) {
					return false;
				}
				if(! checkPassword(newPasswordVarValue, '新密码')) {
					return false;
				}
				if(! checkPassword(newPasswordConfirmVarValue, '确认新密码')) {
					return false;
				}
				if(oldPasswordVarValue == newPasswordVarValue){
					Ext.MessageBox.alert('提示', '新密码不能与旧密码相同!');
					return false;
				}
				if(newPasswordVarValue != newPasswordConfirmVarValue) {
					Ext.MessageBox.alert('提示', '确认新密码与新密码不一致!');
					return false;
				}
				return true;

			}

			// 检验密码，包括长度为八位，必需为数字与字母混合等
			function checkPassword(password, name) {
			    if(password == null || password == ''){
			        Ext.MessageBox.alert('提示', name + '不能为空');
			        return false;
			    }
			    if(password.length < 8){
			        Ext.MessageBox.alert('提示', name + '长度必须大于八位');
			        return false;
			    }
		        var pwdPtn = /^[\x21-\x7e]*$/;
		       	if(!pwdPtn.test(password)){
		       	    Ext.MessageBox.alert('提示', name + '必须由英文字母、数字和特殊字符组成，必须同时包含字母和数字');
		       	    return false;
		       	}

			    //判断是否数字和英文组成
			    var numFlag = false;
			    var charFlag = false;
			    for(var i =0; i < password.length; i++){
			        if(password.charAt(i) >= '0' && password.charAt(i) <= '9'){
			            numFlag = true;
			        }
			        if( ( password.charAt(i) >= 'A' && password.charAt(i) <= 'Z' ) || ( password.charAt(i) >= 'a' && password.charAt(i) <= 'z' )){
			            charFlag = true;
			        }
			    }
			    if(! charFlag || ! numFlag ) {
			        Ext.MessageBox.alert('提示', name + '必须由英文字母、数字和特殊字符组成，必须同时包含字母和数字');
					return false;
			    }
			    return true;
			}



			function changePasswordSuccess(form, action) {
				if (action.result.msg) {
					Ext.Msg.alert("提示",action.result.msg);
				} else {
					changePasswordWin.hide();
					changePasswordForm.getForm().reset();
					Ext.MessageBox.alert("成功", "修改密码成功,将在您下一次登录时生效!");
				}
			}


			function changePasswordFailure(form, action) {
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



		    function closeAllTab() {
		        var ts = tabs.findBy(function() {
		            if (this.id != 'home' 
		                && this.id != 'homemain' 
		                && this.id != 'homemain' 
		                && this.id != 'homebar' 
		                && this.id != 'homemsg'
		                && this.id != 'homebtn' 
		                && this.id != 'homehelp') {
		                return true;
		            }
		        });

		        for (i = 0; i < ts.length; i++) {
		            tabs.remove(ts[i]);
		        }
		    }


			function showHelp(helpUrl) {
				if (helpUrl && helpUrl.length > 0) {
					window.open('' + helpUrl, '_blank', 'height=600,width=800,resizable=yes,scrollbars=yes');
				}
			}

			function logoutConfirmed(s) {
				if (s == 'yes') {
					window.location.href='<%=basePath %>/login/logout';
				}
			}

			function logout() {
				Ext.MessageBox.confirm("退出登录", "确定退出登录?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", logoutConfirmed);
			}


			function openMenu(item, url, desc, helpUrl) {
			    if(url != null && url != ''){
					openPage(item.id, item.text, desc, helpUrl, url, true);
			    }
			}


			function openTab(id, text, desc, helpUrl, url) {
				if(url != null && url != ''){
					openPage(id, text, desc, helpUrl, url, true);
				}
			}

			function refreshTab(id, text, desc, helpUrl, url) {
				refreshPage(id, text, desc, helpUrl, url, true);
			}

			function openPage(id, text, desc, helpUrl, url, closable) {
				var tab = tabs.getItem(id);
				if (typeof(tab) == "undefined" || tab == null) {
					tabs.add(createTabPane(id, text, desc, helpUrl, url, closable)).show();
		        } else {
		        	tabs.setActiveTab(tab);
		        }
			}

			function refreshPage(id, text, desc, helpUrl, url, closable) {
				var tab = tabs.getItem(id);
				if (typeof(tab) != "undefined" && tab != null) {
					tabs.remove(tab);
		        }
		        tabs.add(createTabPane(id, text, desc, helpUrl, url, closable)).show();
			}

			function createTabPane(id, text, desc, helpUrl, url, closable) {
				var tabPane = new Ext.Panel({
						id: id,
						title: text,
						border: false,
						closable: closable,
						layout: "border",
						items: [
							new Ext.Panel({
								region: "north",
								cls: "x-toolbar",
								height: 25,
								border: false,
								layout: "border",
								items: [
									new Ext.Panel({
										region: "east",
										width: 44,
										border: false,
										layout: "border",
										items: [
											new Ext.Panel({
												region: "west",
												width: 22,
												border: false,
												layout: "form",
												items: [
													new Ext.Button({
														cls: "x-btn-icon",
														icon: "<%=basePath %>/assets/images/frame/new.gif",
														tooltip: "在新的标签页中打开",
														style: "background-color: #FFDF8C;",
														handler: function(){openPage(id + '1', text, desc, helpUrl, url, closable);}
													})
												]
											}),

											new Ext.Panel({
												region: "center",
												border: false,
												layout: "form",
												items: [
													new Ext.Button({
														cls: "x-btn-icon",
														icon: "<%=basePath %>/assets/images/frame/help.gif",
														tooltip: "帮助",
														style: "background-color: #FFDF8C;",
														handler: function(){showHelp(helpUrl);}
													})
												]
											})
										]
									}),

									new Ext.Panel({
										region: "center",
										border: false,
					            		html: "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='25' style='background-color: #FFDF8C;'><tr><td width='24' align='center'><img src='<%=basePath %>/assets/images/frame/info.gif' border='0' height='16' width='16'/></td><td>" + desc + "</td></tr></table>"

					        		})
								]
					        }),

					        new Ext.Panel({
								region: "center",
								border: false,
					            html: "<iframe name='frame_" + id + "' src='" + url + "' width='100%' height='100%' frameborder='0'></iframe>"
					        })
			        	]
			        });
				return tabPane;
			}



			var mask = null;

			// 传入的参数为不被遮罩的元素

			function showModal(win) {
				if (mask == null) {
					if (win && win.el && win.el.dom) {
						mask = framePane.container.createChild({cls:"ext-el-mask"}, win.el.dom);
					} else {
						userWin.show();
						userWin.hide();
						mask = framePane.container.createChild({cls:"ext-el-mask"}, userWin.el.dom);
					}

				}
				Ext.getBody().addClass("x-body-masked");
				mask.setSize(Ext.lib.Dom.getViewWidth(true), Ext.lib.Dom.getViewHeight(true));
				mask.show();
			}



			function hideModal() {
				mask.hide();
				Ext.getBody().removeClass("x-body-masked");
			}



			function requestBlank() {
				Ext.Ajax.request({
					url: 'blank.action',
					method: 'GET',
					disableCaching: true,
					success: function (result, request) {
						if (result.responseText.indexOf('okokokokokok') == -1) {
							window.location.href='logout.action';
						}
					}

				});

			}



			try{

				setInterval("requestBlank()", 1801000);

			} catch(e) {

			}

</script><style type="text/css"></style>
</body>
</html>