import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import s from './Header.module.css';
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "../reducer/UserDataSlice";

const Header = () => {
    const nav = useNavigate();
    const dispatch = useDispatch();
    const {data,status,error} = useSelector((state)=>state.user);
    const location = useLocation();
    const [user, setUser] = useState({});
    const [isLogin, setIsLogin] = useState(false);

     
    const logout = () => {
        localStorage.removeItem('token');
        setIsLogin(false);
        setUser({});
        nav('/');
    }

    useEffect(() => {
        const token = localStorage.getItem('token');
        if(token){
            if(status !== 'success'){
                //success가 아닐 때만 요청 함.
                dispatch(getUser());                
    
            } else if(status === 'success'){
                //만약 success면
                setUser(data);
                setIsLogin(true);
          } else {
            setIsLogin(false);
          }
        }
    }, [data]);

    if(location.pathname === '/login') {
        return null;
    }
    return(
        <header className={s.header}>
        <div className={s.title} onClick={()=>nav('/')}>CodeShowCase</div>
        {
            isLogin ?
            <div className={s.userInforBox}>
                <button onClick={logout} className={s.logout}>로그아웃</button>
                <div className={s.profileContainer} onClick={()=>nav('/mypage')} title="마이페이지로 가기">

                <div className={s.userInfor}>{user.userNickname}</div> 
                <img
                    src={user.profileImage}
                    alt="프로필 사진"
                    className={s.profileImage}
                    onError={(e) => {e.target.src = './profile/default-profile.png'}}
                />
                </div>
            </div>
            :
            <button onClick={()=>nav('/login')}>로그인</button>
        }
        
      </header>
    )
}

export default Header;