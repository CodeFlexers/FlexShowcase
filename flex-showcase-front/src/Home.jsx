import { useEffect, useState } from "react";
import ProfileCard from "./ProfileCard";
import "./App.css"
import api from "./common/api";



const Home = () => {

    const [users, setUsers] = useState([]);

    const getAllUsers = async() => {

        const res = await api.get("/main/user-infor");
        console.log(res.data);
        
        setUsers(res.data);

    }

    useEffect(() => {
        getAllUsers();

    }, [])

 return <div className="Home">
    
    <div style={{height: "300px", }}>사진</div>

    <div>
        <h2>Introduce</h2>
        <div>안녕하세요, <b>CodeFlexers</b>입니다. <br />
            팀명은 <b>Code</b>를 유연하고 자유자재로 작성할 수 있다는 뜻으로 지었습니다.<br />
            이 사이트는 직접 구현한 기능을 보여주고 실행할 수 있도록 구현한 사이트입니다.
        </div>
    </div>

    <div>
        <h2>Profile</h2>
        <div className="profile-cardList">
            {users.map((user, idx) => 
                <ProfileCard key={idx} user={user}/>
            )}

        </div>
    </div>



    {/* <div>
        <h2>00의 ShowRoom</h2>
        
    </div> */}




 </div>

}

export default Home;