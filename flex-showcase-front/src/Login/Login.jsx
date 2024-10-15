import { useState } from "react";
import api from "../common/api";
import { useLocation, useNavigate } from "react-router-dom";

const Login = () => {
    const nav = useNavigate();
    const [id, setId] = useState("");   
    const [pw, setPw] = useState("");
    const location = useLocation();
    const {message} = location.state || {};

    // decode token : jwtDecode(localStorage.getItem('token'))

    const login = async() => {
        try{
            const res = await api.post("/login", {username: id, password: pw});
            localStorage.setItem('token', res.headers.get("Authorization"));
            nav("/");
        } catch(err){
            alert('fail');
        }

    }

    return <div>

    { message ? <div>{message}</div> : <></>}
    Id <input type="text" value={id} placeholder="아이디" onChange={(e) => setId(e.target.value)}/>
    Pw <input type="password" value={pw} placeholder="비밀번호" onChange={(e) => setPw(e.target.value)}/>
    <button onClick={login}>로그인</button>
    </div>;
}

export default Login;