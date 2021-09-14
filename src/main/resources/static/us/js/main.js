//back to prev page
function back_page() {
    window.history.back();
}

//login
function login() {
    var conf = confirm("Bạn cần phải đăng nhập để có thể bình luận !");
    if (conf == true) {
        window.location.replace("https://localhost:8085/dang-nhap");
    }

}

//update view
var clickView = document.getElementsByClassName('click');
var id = document.getElementsByClassName('hiden');
for (let i = 0; i <= clickView.length - 1; i++) {
    clickView[i].addEventListener("click", function () {
        $.ajax({
            url: '/bai-viet/' + id[i].value + '/',
            type: "GET",
            data: {
                'up_view': 1
            }
        })
    })
}

// comment process
function comment(avatar, name, date, content, index) {
    if (index === 0) {
        return `
            <div class="vcard">
                <img src="${avatar}" alt="Image placeholder">
            </div>
            <div class="comment-body">
                <h3>${name}</h3>
                <div class="meta">${date}</div>
                <p>${content}</p>
                <div class="rep">Trả lời</div>
                <ul class="children"></ul>
                <div style="display: none;" class="commentBox">
                    <input class="commentInput" type="text" name=""
                           placeholder="Viết bình luận">
                    <p sec:authorize="isAuthenticated()" class="commentButton"
                       th:attr="onclick=|addReply(0)|">
                        <i class="fa fa-paper-plane"></i>
                    </p>
                </div>
            </div>
        `
    }
    return `
        <div class="vcard">
            <img src="${avatar}" alt="Image placeholder">
        </div>
        <div class="comment-body">
            <h3>${name}</h3>
            <div class="meta">${date}</div>
            <p>${content}</p>
        </div>
        
    `
}


function addNewComment() {
    var commentInput = document.getElementById('p-5-commentInput').value;
    if(commentInput===''){
        alert('Bình luận không được để trống !');
    }else {
        var newComment = document.getElementById('newComment');
        var aId = document.getElementById('aId').value;
        var pId = document.getElementById('pId').value;

        document.getElementById('p-5-commentInput').value = '';
        $.ajax({
            url: "/binh-luan/them/",
            type: "GET",
            data: {
                'p_i': pId,
                'a_i': aId,
                'c_i': 0,
                'commentInput': commentInput
            }
        }).done(function () {
            location.reload();
        });
    }

}

function addReply(i) {
    var commentInput = document.getElementsByClassName('commentInput')[i].value;
    if(commentInput === ''){
        alert('Bình luận không được bỏ trống');
    }else {
        var p_i = document.getElementsByClassName('p_i')[i].value;
        var c_i = document.getElementsByClassName('c_i')[i].value;
        var child = document.getElementsByClassName('children')[i];
        document.getElementsByClassName('commentInput')[i].value = '';
        $.ajax({
            url: "/binh-luan/them/",
            type: "GET",
            data: {
                'p_i': p_i,
                'a_i': document.getElementsByClassName('a_i')[i].value,
                'c_i': c_i,
                'commentInput': commentInput
            }
        }).done(function (data) {
            var cmt = document.createElement("li");
            cmt.innerHTML = comment(data.avatar, data.fullName, data.commentDate, data.commentContent,1);
            cmt.classList.add("comment");
            child.appendChild(cmt.cloneNode(true));

        });
    }
}

/*
var child = document.getElementsByClassName('children')[0];
function test() {
    $.ajax({
        url : "/binh-luan/test-api/",
        type : "POST"
    }).done(function (data) {
        for (let i = data.length-1; i >=0; i--) {
            var cmt = document.createElement("li");
            cmt.innerHTML = comment(data[i].avatar,data[i].fullName,data[i].commentDate,data[i].commentContent);
            cmt.classList.add("comment");
            child.appendChild(cmt.cloneNode(true));
        }
    });
}*/

var tl = document.getElementsByClassName('rep');
var commentBox = document.getElementsByClassName('commentBox');
for (let i = 0; i < tl.length; i++) {
    window.addEventListener("mouseup", function (even) {
        if (even.target != tl[i] && even.target.parentNode != tl[i] && even.target != commentBox[i] && even.target.parentNode != commentBox[i]) {
            tl[i].style.display = 'block';
            commentBox[i].style.display = 'none';
        } else {
            tl[i].style.display = 'none';
            commentBox[i].style.display = 'flex';
        }
    })
}
