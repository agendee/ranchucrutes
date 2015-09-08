<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@ attribute name="msg" required="true" rtexprvalue="true" type="java.lang.String"%>
<div class="col-md-offset-2 col-md-8 container">
    <div class="panel panel-success">
          <div class="panel-heading">
            <h3 class="panel-title">${title}</h3>
          </div>
          <div class="panel-body">
                ${msg}
           </div>
    </div>
</div>