<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="./style.scss">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
    <script src="index.js"></script>
    
    <title>Professor</title>
    <script>
    </script>
</head>
<body>
    <header class="row bg-primary text-white ">
        <div id="home-button" class="col-1">
            <button type="button" class="btn btn-primary">
                Home Page
            </button>
        </div>
        <div class="col-2">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#AddAluno">
                Adicionar Aluno
            </button>
        </div>
        <div class="col-1 offset-7">
            Professor
        </div>
        <div class="col-1">
            <a href="/SistemaEscolar">
                <button type="button" class="btn btn-primary">
                    Log out
                </button>
            </a>
        </div>
    </header>
    <div id="main-page" class="container">
<!--        <div id="chart-row" class="row" style="margin:1rem">
            <div id="chart1" style="width: 12rem;height: 12rem;" class="col"></div>
            <div id="chart2" style="width: 12rem;height: 12rem;" class="col"></div>
        </div>
        <div id="chart2-row" class="row" style="margin:1rem">
            <div id="chart3" style="width: 12rem;height: 12rem;" class="col"></div>
            <div id="chart4" style="width: 12rem;height: 12rem;" class="col"></div>
        </div>-->
        <div class="row">
            <div class="col">
                <div class="input-group mb-3">
                    <div class="input-group-append">
                        <button class="btn btn-outline-secondary" type="button" id="button-addon2" data-toggle="modal" data-target="#AddAluno">+</button>
                    </div>
                    <input type="text" class="form-control" placeholder="Nome do Aluno" aria-label="Recipient's username" aria-describedby="button-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-outline-secondary" type="button" id="button-addon2">Pesquisar</button>
                    </div>
                  </div>
            </div>
        </div>
        <table id="table-alunos" class="table table-hover">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Nome</th>
                <th scope="col">Av1</th>
                <th scope="col">Av2</th>
                <th scope="col">Av3</th>
                <th scope="col">Menor Nota</th>
                <th scope="col">Maior Nota</th>
                <th scope="col">Media</th>
                <th></th>
              </tr>
            </thead>
            <tbody id="table-alunos-body">
            </tbody>
        </table>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="AddAluno" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Cadastro de Novo Aluno</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="nomeAluno">Nome do Aluno</label>
                            <input type="text" class="form-control" id="newAlunoNome" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="nomeAluno">Matricula</label>
                            <input type="text" class="form-control" id="newAlunoMatricula" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="nomeAluno">Identificação do Curso</label>
                            <input type="text" class="form-control" id="newAlunoIdCurso" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="nomeAluno">Identificação da Disciplina</label>
                            <input type="text" class="form-control" id="newAlunoIdDisciplina" aria-describedby="emailHelp">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <button onClick="newAluno()" type="button" class="btn btn-primary"   data-dismiss="modal">Salvar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="EditAluno" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Cadastro do Aluno</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                     <form>
                         <div class="form-group">
                            <label for="nomeAluno">Identificação da Avaliação</label>
                            <input type="text" disabled="true" class="form-control" id="alunoIdAvaliacao" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="nomeAluno">Nome do Aluno</label>
                            <input type="text" class="form-control" id="alunoNome" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="nomeAluno">Matricula</label>
                            <input type="text" class="form-control" id="alunoMatricula" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="nomeAluno">Identificação do Curso</label>
                            <input type="text" class="form-control" id="alunoIdCurso" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="nomeAluno">Identificação da Disciplina</label>
                            <input type="text" class="form-control" id="alunoIdDisciplina" aria-describedby="emailHelp">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Nota da Av1</label>
                            <input type="number" step="0.01" class="form-control" id="alunoAv1">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Nota da Aps1</label>
                            <input type="number" step="0.01" class="form-control" id="alunoAps1">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Nota da Av2</label>
                            <input type="number" step="0.01" class="form-control" id="alunoAv2">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Nota da Aps2</label>
                            <input type="number" step="0.01" class="form-control" id="alunoAps2">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Nota da Av3</label>
                            <input type="number" step="0.01" class="form-control" id="alunoAv3">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <button onClick="updateAluno()" type="button" class="btn btn-primary"   data-dismiss="modal">Salvar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>