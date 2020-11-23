/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function addAlunoRow(aluno){
    $('#table-alunos-body').append(''+
        '<tr inf='+ aluno.nome+'>'+
            '<th scope="row">'+aluno.index+'</th>'+
            '<td>'+aluno.nome+'</td><td>'+(aluno.nota1)+'</td><td>'+(aluno.nota2)+'</td><td>'+aluno.nota3+'</td><td>'+ Math.min((aluno.nota1),(aluno.nota2),aluno.nota3) +'</td><td>'+Math.max((aluno.nota1),(aluno.nota2),aluno.nota3)+'</td><td>'+ aluno.media+'</td>'+
            `<td>
                <button class="btn btn-light" data-toggle="modal" data-target="#EditAluno" onClick='fillModalAluno(${JSON.stringify(aluno)})'>
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                        <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                    </svg>
                </button>
                <button class="btn btn-light" onClick='deleteAluno(${aluno.idAvaliacao})'>
                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-trash-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                      </svg>
                </button>
            </td>
        </tr>`);
}

function searchAluno(){
    let trs = $('tr');
    let searchValue = $('#input-search').val();
    for(let i=0; i < trs.length; i++){
        const element = trs[i];
        $(element).show();
        let infAluno = $(element).attr('inf');
        if(infAluno && infAluno.indexOf(searchValue)<0){
            $(element).hide();
        }
    }
}

function inputChange(){
    searchAluno();
}

function fillModalAluno(aluno){    
    $('#alunoNome').val(aluno.nome);
    $('#alunoMatricula').val(aluno.matricula);
    $('#alunoIdCurso').val(aluno.idCurso);
    $('#alunoIdDisciplina').val(aluno.idDisciplina);
    $('#alunoIdAvaliacao').val(aluno.idAvaliacao);
    $('#alunoAv1').val(aluno.av1||0);
    $('#alunoAv2').val(aluno.av2||0);
    $('#alunoAps1').val(aluno.aps1||0);
    $('#alunoAps2').val(aluno.aps2||0);
    $('#alunoAv3').val(aluno.av3||0);
}

function updateTable(){
    $('#table-alunos-body').empty();
    $.get("/SistemaEscolar/aluno", function(data, status){
                let arrayAlunos = JSON.parse(data);
                let arrayAlunosNotas = [];
                let alunoIndex=0;
                for( i=0; i<arrayAlunos.length; i++ ){
                    const aluno = arrayAlunos[i];
                    
                    $.get("/SistemaEscolar/avaliacao?matricula="+aluno.matricula,function(stringAvaliacao,status){
                        if(status!=='success')return;
                        let arrayAvaliacao = JSON.parse(stringAvaliacao);
                        for (j=0; j<arrayAvaliacao.length; j++){
                            
                            const avaliacao = arrayAvaliacao[j];
                            let notaAluno = {...aluno,...avaliacao};
                            alunoIndex++;
                            notaAluno.index = alunoIndex;
                            notaAluno.nota1 = notaAluno.av1 + notaAluno.aps1;
                            notaAluno.nota2 = notaAluno.av2 + notaAluno.aps2;
                            notaAluno.nota3 = notaAluno.av3;
                            notaAluno.media = (
                                        (Math.min(notaAluno.nota1,notaAluno.nota2)===notaAluno.nota1?Math.max(notaAluno.nota1,notaAluno.nota3)+notaAluno.nota2:Math.max(notaAluno.nota2,notaAluno.nota3)+notaAluno.nota1)/2
                                    ).toFixed(2);
                            addAlunoRow(notaAluno);
                        }
                    });
                };
              });
}

function newAluno(){
    let aluno = {};
    aluno.nome = $('#newAlunoNome').val();
    aluno.matricula = $('#newAlunoMatricula').val();
    aluno.idCurso = $('#newAlunoIdCurso').val();
    aluno.idDisciplina = $('#newAlunoIdDisciplina').val();
    aluno.idUsuario=1;
    $.ajax({
        type: "POST",
        url: '/SistemaEscolar/aluno',
        data: JSON.stringify(aluno),
        success:success,
        contentType: "application/json",
        dataType: 'json'
      });
      
    function success(data,status){
        if(status!=='success')return;
        aluno.nomeAluno= aluno.nome;
        aluno.idAvaliacao = Math.round(Date.now()/100000000)*Math.random();

        $.ajax({
            type: "POST",
            url: '/SistemaEscolar/avaliacao',
            data: JSON.stringify(aluno),
            success: updateTable,
            contentType: "application/json",
            dataType: 'json'
          });
    }
}

function updateAluno(){
    let aluno = {};
    aluno.nome = $('#alunoNome').val();
    aluno.matricula = $('#alunoMatricula').val();
    aluno.idCurso = $('#alunoIdCurso').val();
    aluno.idAvaliacao = $('#alunoIdAvaliacao').val();
    aluno.idDisciplina = $('#alunoIdDisciplina').val();
    aluno.av1 = $('#alunoAv1').val();
    aluno.av2 = $('#alunoAv2').val();
    aluno.aps1 = $('#alunoAps1').val();
    aluno.aps2 = $('#alunoAps2').val();
    aluno.av3 = $('#alunoAv3').val();
    
    $.ajax({
        type: "PUT",
        url: '/SistemaEscolar/aluno',
        data: JSON.stringify(aluno),
        success: updateTable,
        contentType: "application/json",
        dataType: 'json'
    });
    
    $.ajax({
        type: "PUT",
        url: '/SistemaEscolar/avaliacao',
        data: JSON.stringify(aluno),
        success: updateTable,
        contentType: "application/json",
        dataType: 'json'
    });
}

function deleteAluno(idAvaliacao){
    $.ajax({
        type: "DELETE",
        url: '/SistemaEscolar/avaliacao?idAvaliacao='+idAvaliacao,
        success: updateTable,
        contentType: "application/json",
        dataType: 'json'
    });
    
    
}

$(document).ready(()=>{
            updateTable();
            
            
//            let dataChar1 ={
//                animationEnabled: true,
//                title: {
//                    text: "Media dos Alunos"
//                },
//                data: [{        
//                    type: "spline",
//                    indexLabelFontSize: 16,
//                    dataPoints: [
//                        { y:8.5},
//                        { y:10},
//                        { y:5.5},
//                        { y:9.5},
//                        { y:6.5},
//                        { y:8.0},
//                        { y:7.5},
//                        { y:8.0},
//                        { y:8.5},
//                        { y:6.5},
//                        { y:8.5},
//                        { y:8.5},
//                        { y:2.21},
//                        { y:1.2},
//                        { y:8.78},
//                        { y:8.2},
//                        { y:8.2},
//                        { y:8.1}
//                    ]
//                }]
//            };
//            let dataChar2 = {
//                animationEnabled: true,
//                theme: "light2",
//                title:{
//                    text: "Desvio PadrÃ£o"
//                },
//                data: [{        
//                    type: "spline",
//                    indexLabelFontSize: 16,
//                    dataPoints: [
//                        { x:-3,y: 10 },
//                        { x:-2,y: 20 },
//                        { x:0 ,y: 60 },
//                        { x:2 ,y: 20 },
//                        { x:3 ,y: 10 }
//                    ]
//                }]
//            };
//            let dataChar3 ={
//                animationEnabled: true,
//                title: {
//                    text: "Quartis das Notas"
//                },
//                axisY: {
//                    title: "Porcentagem das Notas %"
//                },
//                data: [{
//                    type: "column",
//                    // startAngle: 240,
//                    showInLegend: true,
//                    yValueFormatString: "##0.00\"%\"",
//                    indexLabel: "{label} {y}",
//                    dataPoints: [
//                        {y: 10, label:"Q1"},
//                        {y: 20, label:"Q2"},
//                        {y: 30, label:"Q3"},
//                        {y: 50, label:"Q4"}
//                    ]
//                }]
//            };
//            let dataChar4 ={
//                animationEnabled: true,
//                title: {
//                    text: "Maiores e Menores Notas"
//                },
//                axisY: {
//                    title: "Porcentagem das Notas %"
//                },
//                data: [{
//                    type: "column",
//                    // startAngle: 240,
//                    showInLegend: true,
//                    yValueFormatString: "##0.00",
//                    indexLabel: "{label} {y}",
//                    dataPoints: [
//                        {y: 0},
//                        {y: 1},
//                        {y: 2},
//                        {y: 3},
//                        {y: 4},
//                        {y: 5},
//                        {y: 6},
//                        {y: 7},
//                        {y: 8},
//                        {y: 9},
//                        {y: 10}
//                    ]
//                }]
//            };
//
//            var chart1 = new CanvasJS.Chart("chart1", dataChar1);
//            chart1.render();
//            var chart2 = new CanvasJS.Chart('chart2', dataChar2);
//            chart2.render();
//            var chart3 = new CanvasJS.Chart("chart3", dataChar3);
//            chart3.render();
//            var chart4 = new CanvasJS.Chart("chart4", dataChar4);
//            chart4.render();
            
        });