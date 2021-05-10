// <%@page contentType="text/html; charset=utf-8" %>

Ext.QuickTips.init();

/** 分页大小 */
var pageSize = 25;  
/** 请求有效时间十五分钟 */
Ext.Ajax.timeout = 15 * 60 * 1000;
Ext.HOME = "../assets/ext-3.4.0/" ;
Ext.BLANK_IMAGE_URL = Ext.HOME + "resources/images/default/s.gif";
Ext.Msg.minWidth = 160;

/** 最大长度计算汉字处理函数 */
maxLengthOverride = function(value) {
	if (this.allowBlank == false) {
		if (value == null || value == '') {
			this.markInvalid(String.format(this.blankText, value));
			return false;
		}
	}
	var maxLen = this.maxLength;
	var maxLenText = this.maxLengthText;
	if (maxLen != null && maxLen != 'undefined' && maxLen > 0) {
		var regex = /[^\x00-\xff]/g;
		var len;
		var repalceValue = value.replace(regex, '***');
		len = repalceValue.length;
	}
	if (len > maxLen) {
		this.markInvalid(String.format(maxLenText, value));
		return false;
	}
	return true;
};

/**
 * 重写Grid.Column.renderer 每列悬停时提示内容信息
 */
Ext.override(Ext.grid.Column, {
	renderer : function(value, metadata, record, rowIdx, colIdx, ds) {
		if (this.rendererCall) {
			var ret = this.rendererCall(value, metadata, record,
					rowIdx, colIdx, ds);
				return '<div ext:qtitle="' + this.header + '" ext:qtip="'
						+ (ret == null ? "" : ret) + '">'
						+ (ret == null ? "" : ret) + '</div>';
			return ret;
		} else {
				return '<div ext:qtitle="' + this.header + '" ext:qtip="'
						+ (value == null ? "" : value) + '">'
						+ (value == null ? "" : value) + '</div>';
			return value;
		}
	}
});

/**
 * 重写GridPanel 隐藏列表头的头部下拉菜单
 */
Ext.override(Ext.grid.GridPanel, {
	enableHdMenu : false,
	loadMask:true
});
		
/**
 * 重写Ext.grid.RowNumberer
 * 使GRID分页时实现序号自增
 */
/*
Ext.override(Ext.grid.RowNumberer,{
	renderer : function(value, cellmeta, record, rowIndex, columnIndex,store) {
		if(store.lastOptions && store.lastOptions.params){
			return store.lastOptions.params.start + rowIndex + 1;
		}
		return rowIndex + 1;
	}
});
*/

/** 日期格式 yyyy-MM-dd H:i */
Ext.renderDate = function(value) {
	if (value != null) {
		value = value.replace("T", " ");
		if (value.length == 19) {
			value = value.substr(0, 16);
		}
	}
	return value;
};

/**
 * 给所有设置为readOnly的输入灰色显示
 */
Ext.override(Ext.form.TriggerField, {
    setReadOnly: function(readOnly){
        if(readOnly != this.readOnly){
            this.readOnly = readOnly;
            this.updateEditState();
        }
        if(this.el){
	        if(this.readOnly){
	        	this.el.setStyle('color', "#C0C0C0");
	        }else{
	        	this.el.setStyle('color', "#000000");
	        }
        }
    }
});
/**
 * 把只读的输入框的label的*号，变成红色
 */
Ext.override(Ext.form.TextField, {
	initComponent : function(){
		if (this.allowBlank === false) {
			if (this.fieldLabel && (this.fieldLabel.indexOf("*") < 0)) {
				this.labelSeparator = '<span style="color:#FF0000;">*</span>:';
			}
		}
        Ext.form.TextField.superclass.initComponent.call(this);
        this.addEvents(
            'autosize',
            'keydown',
            'keyup',
            'keypress'
        );
    }
});


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
/**
 * 表单提交失败事件
 * @param form
 * @param action
 */
function showFormSubmitFailureInfo(form, action) {
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

