function AttachmentField(fID, fText, fType, size) {
	this.fieldType = "AttachmentField";
	this.fieldID = fID;
	this.fileType = fType;
	this.sizeLimit = size;
	this.require = false;

	this.isVisiable = true;
	this.isReadonly = false;

	var display = $("#" + this.fieldID + "_ubfbox").css("display");
	if (display == "none")
		this.isVisiable = false;
}
AttachmentField.prototype.G = function() {
	var attvalue = document.getElementById(this.fieldID).value;
	return attvalue;
}
AttachmentField.prototype.S = function(val) {
	document.getElementById(this.fieldID).value = val;
}
AttachmentField.prototype.setFileSize = function(size) {
	try {
		var attTag = eval(this.fieldID + "_tag");
		attTag.setFileSizeLimit(size);
		this.sizeLimit = size;
		return true;
	}
	catch(e) {
		return false;
	}
}
AttachmentField.prototype.visiable = function(isshow) {
	if (isshow)
		$("#" + this.fieldID + "_ubfbox").css("display", "block");
	else
		$("#" + this.fieldID + "_ubfbox").css("display", "none");
	this.isVisiable = isshow;
};