/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function readCookie(name){
    var nameEQ = name + "=";
    cookieArray = document.cookie.split(";");
    for (var i = 0; i < cookieArray.length; i++) {
        var c = cookieArray[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null ;
}

let actualAluno={
    matricula:readCookie("login")
};

function addAlunoRow(aluno){
    $('#table-notas').append(''+
        '<tr>'+
            '<th scope="row">'+aluno.index+'</th>'+
            '<td>'+aluno.nomeDisciplina+'</td><td>'+(aluno.nota1)+'</td><td>'+(aluno.nota2)+'</td><td>'+aluno.nota3+'</td>'+ 
        `</tr>`);
}

function updateTable(){
    let alunoIndex=0;
    $('#table-notas').empty();                    
    $.get("/SistemaEscolar/avaliacao?matricula="+actualAluno.matricula,function(stringAvaliacao,status){
        if(status!=='success')return;
        let arrayAvaliacao = JSON.parse(stringAvaliacao);
        for (j=0; j<arrayAvaliacao.length; j++){
            const avaliacao = arrayAvaliacao[j];
            $.get("/SistemaEscolar/disciplina?idDisciplina="+ avaliacao.idDisciplina,function(stringDisciplina,status){
                let disciplina = JSON.parse(stringDisciplina);
                let notaDisciplina = {...disciplina, ...avaliacao};
                alunoIndex++;
                notaDisciplina.index = alunoIndex;
                notaDisciplina.nota1 = notaDisciplina.av1 + notaDisciplina.aps1;
                notaDisciplina.nota2 = notaDisciplina.av2 + notaDisciplina.aps2;
                notaDisciplina.nota3 = notaDisciplina.av3;
                console.log(notaDisciplina);
                addAlunoRow(notaDisciplina);
            })
        }
    });
}

$(document).ready(()=>{
    updateTable();
});