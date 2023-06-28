let index = {
    init:function(){
        $("#btn-save").on("click",()=>{
        this.save();
        });
    },

    save:function(){
        //alert('user의 save함수 호출됨');
        let data = {
             username:$("#username").val(),
             password:$("#password").val(),
             email:$("#email").val()
        };

        //console.log(data);

        //ajax 호츨은 비동기 (default)
        $.ajax({
            type:"POST",
            url:"/blog/api/user",
            data:JSON.stringify(data), // http body 부분
            contentType:"application/json; charset=utf-8",
            dataType:"json" // 응답 데이터 타입이 json 이면 javascript object 로 파싱하기
        }).done(function(resp){
            alert("회원 가입이 완료되었습니다.");
            //console.log(resp);
            location.href="/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));
        }); //ajax 통신 이용, data 를 json 으로 변환 및 insert 요청 하기

        }
}

index.init();
