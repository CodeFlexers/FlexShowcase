import { useState } from "react";
import api from "../../common/api";
import { useNavigate } from "react-router-dom";

const Join = () => {

    const nav = useNavigate();

    const [id, setId] = useState("");
    const [pw, setPw] = useState("");
    const [nickname, setNickname] = useState("");

    const join = async() => {

        try{
            const res = await api.post("/join", {userId: id, userPw: pw, userNickname : nickname})
            nav("/login", {state: {message: res.data}});
        }catch(err){
            alert(err.response.data);
        }

    }




    return <div>

    Id <input type="text" value={id} placeholder="아이디" onChange={(e) => setId(e.target.value)}/>
    Pw <input type="password" value={pw} placeholder="비밀번호" onChange={(e) => setPw(e.target.value)}/>
    Nickname <input type="text" value={nickname} placeholder="닉네임" onChange={(e) => setNickname(e.target.value)}/>
    
    <button onClick={join}>가입하기</button>
    </div>;
}

export default Join;