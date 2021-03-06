<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="./style.scss">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
</head>
<body>
    <header class="row bg-primary text-white ">
        <div id="home-button" class="col-1">
            <button type="button" class="btn btn-primary">
                Home Page
            </button>
        </div>
        <div class="col-2">
        </div>
        <div class="col-1 offset-8">
            Login Page
        </div>
    </header>
    <div class="container">
        <div class="row text-right">
            <div class="col-8 border offset-2 mt-5">
                <form class="m-4" action="login" method="post">
                    <div class="row border-bottom mb-2 text-center">
                        <h2 class="col">
                            Acesso ao Sistema
                        </h2>
                    </div>
                    <div class="form-group row">
                      <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                      <div class="col-sm-10">
                          <input type="text" class="form-control" id="staticEmail" name="login" >
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                      <div class="col-sm-10">
                          <input type="password" class="form-control" id="inputPassword" name="senha">
                      </div>
                    </div>
                    <button type="cancel" class="btn btn-secondary mb-2">Cancel</button>
                    <button type="submit" class="btn btn-primary mb-2">Login</button>
                </form>
            </div>
        </div>
        <div class="row text-center">
            <div class="col mt-5">
                <h1>Leia o readme</h1>
            </div>
        </div>
    </div>
</body>
</html>