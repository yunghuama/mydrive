<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Upload</title>
  </head>
  <body>
    <script type="text/javascript">
    <s:iterator value="uploadHelper.attachedFiles">
      parent.<s:property value="callback"/>('<s:property value="filePath"/>');
    </s:iterator>
    </script>
  </body>
</html>