import { useEffect, useState } from "react";
import "./MyPage.css"
import api from "./common/api";

const MyPage = () => {

    // 조회한 mypage 데이터 상태 변수 저장
    const [data, setData] = useState({});

    const getMypage = async() => {

        const res = await api.get("/my-page");
        
        setData(res.data);
    }

    useEffect(() => {

        getMypage();

    }, [])

    return <div className="mypage">

        {data && 
        <div className="profile-info">
            <div className="profile-inf">
                <div className="profile-in">
                        <img src={data.profileImage} alt="프로필 사진" className="profile-image" />
                        <div className="user-name">{data.name}</div>
                        <div>email: {data.email}</div>
                        <div>phone: {data.phone}</div>
                </div>

                <div className="profile-edit">

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">닉네임</div>
                        <div>{data.userNickname}</div>
                        {/* <input type="text"/> */}
                    </div>

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">생일</div>
                        <div>{data.birthdate ? `${data.birthdate[0]}.${data.birthdate[1]}.${data.birthdate[2]}` : <></>}</div>
                        {/* <input type="data"/> */}
                    </div>

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">이메일</div>
                        <div>{data.email}</div>
                        {/* <input type="text"/> */}
                    </div>

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">전화번호</div>
                        <div>{data.phone}</div>
                        {/* <input type="text"/> */}
                    </div>

                </div>
            </div>

            <div className="profile-editer">
                <h3>자유롭게 소개해주세요</h3>
                <div dangerouslySetInnerHTML={{__html: data.contentHtml}}></div>
            </div>

            <div className="edit-btn-container">
                <button className="edit-profile-btn">프로필 수정</button>
            </div>

        </div>



        }

    </div>


}

export default MyPage;