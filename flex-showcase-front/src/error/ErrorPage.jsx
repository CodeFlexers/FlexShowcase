import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const ErrorPage = ({code, msg}) => {
    const location = useLocation();
    const nav = useNavigate();

    const [isMsg, setIsMsg] = useState(false);
    useEffect(()=>{
        if(code && msg){
            setIsMsg(true);
        }
    },[]);

    useEffect(()=>{
        console.log(location);
    },[location]);

    return(
        <div>
            {isMsg?
                <div>
                    <div>{code}</div>
                    <div>{msg}</div>
                </div>
                :
                <div>
                    <div>404 NOT FOUND</div>
                    <div>페이지를 찾을 수 없습니다.</div>
                    <button onClick={()=>nav('/')}>홈으로 돌아가기</button>
                </div>
            }
        </div>
    )
}
export default ErrorPage;