/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.defaultLanguage = 'zh-cn';
	config.skin='office2003'; 
	config.font_names = '宋体;楷体_GB2312;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana;';
	config.filebrowserBrowseUrl = '../ckeditor/uploader/browse.jsp';
	config.filebrowserImageBrowseUrl = '../ckeditor/uploader/browse.jsp?type=Images';
	config.filebrowserFlashBrowseUrl = '../ckeditor/uploader/browse.jsp?type=Flashs';
	config.filebrowserUploadUrl = 'upload.action';
	config.filebrowserImageUploadUrl = 'upload.action?type=Images';
	config.filebrowserFlashUploadUrl = 'upload.action?type=Flashs';
	config.filebrowserWindowWidth = '640';
	config.filebrowserWindowHeight = '480';
	config.toolbar_A =
		[
			['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
			['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
			'/',
			['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
			['Link','Unlink','Anchor'],
			['Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
			'/',
			['Styles','Format','Font','FontSize'],
			['TextColor','BGColor'],
			['Maximize', 'ShowBlocks','Preview']
		];
	config.toolbar = 'A';
};
