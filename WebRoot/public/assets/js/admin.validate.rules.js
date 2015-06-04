/**
 * Created by 黄向阳 on 3/17/2015.
 * js 表单校验规则(jquery.validate.js)
 */
//账户验证
jQuery.validator.addMethod("isAccount", function(value, element) {
    var RegExp = /^[a-zA-Z_][a-zA-Z0-9_]{3,15}$/;
    return RegExp.test(value);
    //return true;
}, $.validator.format("用户名由字母、数字和下划线组成，长度为6~15位"))
//数字验证
jQuery.validator.addMethod("isNum", function (value, element) {
    var RegExp = /^\d+$/;
    return RegExp.test(value);
}, $.validator.format("只能为数字!"));

//正数验证
jQuery.validator.addMethod("isPositive", function(value, element) {
    var RegExp = /^[1-9]\d*$/;
    return RegExp.test(value) || this.optional(element);
}, $.validator.format("请输入正数数字"));

//学号验证
jQuery.validator.addMethod("isStudentNum", function (value, element) {
    var RegExp = /^\d+$/;
    return RegExp.test(value) || this.optional(element);
}, $.validator.format("学号必须是12位的数字!"));

//规则名：chinese，value检测对像的值，element检测的对像
$.validator.addMethod("chinese", function (value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return (chinese.test(value)) || this.optional(element);
}, "只能输入中文");

//qq号码
jQuery.validator.addMethod("isQQ", function(value, element) {
    var RegExp = /[1-9][0-9]{4,}/;
    return RegExp.test(value) || this.optional(element);
}, $.validator.format("QQ号码格式不正确"));

// 联系电话(手机/电话皆可)验证
jQuery.validator.addMethod("isPhone", function (value, element) {
   var length = value.length;
   var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
   var tel = /^\d{3,4}-?\d{7,9}$/;
   return this.optional(element) || (tel.test(value) || mobile.test(value));
}, "请正确填写您的联系电话");

// 邮政编码验证
jQuery.validator.addMethod("isZipCode", function (value, element) {
   var tel = /^[0-9]{6}$/;
   return this.optional(element) || (tel.test(value));
}, "请正确填写您的邮政编码");

//年份判断
jQuery.validator.addMethod("isYear", function (value, element) {
    var tel = /^[0-9]{4}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的年份");

// 字符验证
jQuery.validator.addMethod("string", function (value, element) {
   return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "不允许包含特殊符号!");

// 验证值不允许与特定值等于
jQuery.validator.addMethod("notEqual", function (value, element, param) {
   return value != param;
}, $.validator.format("输入值不允许为{0}!"));

//字母数字
jQuery.validator.addMethod("alnum", function (value, element) {
   return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
}, "只能包括英文字母和数字");

// 汉字
jQuery.validator.addMethod("chcharacter", function (value, element) {
   var tel = /^[\u4e00-\u9fa5]+$/;
   return this.optional(element) || (tel.test(value));
}, "请输入汉字");

// 身份证号码验证（加强验证）
jQuery.validator.addMethod("isIdCardNo", function (value, element) {
    return this.optional(element) || /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value) || /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(value);
}, "请正确输入您的身份证号码");

// 手机号码验证
jQuery.validator.addMethod("isMobile", function (value, element) {
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");

//电话号码验证
jQuery.validator.addMethod("isTel", function (value, element) {
    var tel1 = /^\d{7,9}$/;
    var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678
    return this.optional(element) || (tel.test(value)) || (tel1.test(value));
}, "请正确填写您的电话号码");

//日期比较
jQuery.validator.methods.compareDate = function(value, element, param) {
    var startDate = jQuery(param).val();
    var date1 = new Date(Date.parse(startDate.replace("-", "/")));
    var date2 = new Date(Date.parse(value.replace("-", "/")));
    return date1 < date2;
};

