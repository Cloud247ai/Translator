 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Translate</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" 
   integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body style="background: linear-gradient(90deg, rgba(2, 0, 36, 1) 0%, rgba(75, 14, 154, 1) 35%, rgba(0, 212, 255, 1) 100%); font-family: 'Poppins', sans-serif;">
<div class="container mt-5 border border-light p-5 rounded-4 shadow bg-white" style="tranform:translateY(25%); width:50vw;">
    <form action="Translator" method="post" >
    <h1 class="text-primary text-center">Translator App</h1>
    <div class="my-3">
       
        Enter Text:<input type="text" class="form-control " placeholder="Enter text here..." name="textToTranslate" required><br>
    </div>
    <div class="my-3">
        <label class="form-label">Select Language</label>
        <select name="languages" class="form-select">
            <option value="te">Telugu</option>
            <option value="hi">Hindi</option>
            <option value="ta">Tamil</option>
            <option value="ml">Malayalam</option>
            <option value="kn">Kanada</option>
            <option value="ko">Korean</option>
             <option value="fr">French</option>
        </select>
    </div>
        <button type="submit" class="btn btn-primary d-block mx-auto px-4 py-2">Translate</button>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>