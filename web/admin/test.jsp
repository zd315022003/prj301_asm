<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form>
    <select id="mySelect" onchange="toggleDiv()">
      <option value="show">Show Div</option>
      <option value="hide">Hide Div</option>
    </select>
  </form>
  
  <div id="myDiv" style="display: none;">
    This is the content of the div.
  </div>
  
  <script>
  function toggleDiv() {
    var selectOption = document.getElementById("mySelect");
    var divElement = document.getElementById("myDiv");
    
    if (selectOption.value === "show") {
      divElement.style.display = "block"; // Hi?n th? div n?u gi� tr? l� "show"
    } else if (selectOption.value === "hide") {
      divElement.style.display = "none"; // ?n div n?u gi� tr? l� "hide"
    }
  }
  </script>
  