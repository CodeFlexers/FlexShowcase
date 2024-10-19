import { useEffect, useState } from "react";
import "./MyPage.css"
import api from "./common/api";
import { useNavigate } from "react-router-dom";

const MyPage = () => {

    // 조회한 mypage 데이터 상태 변수 저장
    const [data, setData] = useState({});
    const nav = useNavigate();

    const getMypage = async() => {

        // const res = await api.get("/my-page");
        // setData(res.data);
        setData({
            profileImage: "yunha.png",
            name: "yunha",
            userNickname: "차차",
            email: "helena0228@naver.com",
            phone: "01099999999",
            birthdate : [2000, 2, 28],
            contentHtml: "윤하 프로필이에여"
        });
    }

    useEffect(() => {

        getMypage();

    }, [])


    return <div className="mypage">

        {/* 프로필 수정 */}
        {data && 
        <div className="profile-info">

            <div className="profile-inf">
                <div className="profile-in">
                        <img src={data.profileImage} alt="프로필 사진" className="profile-image" />
                        <div className="user-name">{data.name}</div>
                        <div>email: {data.email}</div>
                        <div>phone: {data.phone}</div>
                </div>

                <div className="profile-detail">

                    <div className="profile-detail-container">
                        <div className="profile-detail-title">닉네임</div>
                        <div>{data.userNickname}</div>
                        {/* <input type="text"/> */}
                    </div>

                    <div className="profile-detail-container">
                        <div className="profile-detail-title">생일</div>
                        <div>{data.birthdate ? `${data.birthdate[0]}.${data.birthdate[1]}.${data.birthdate[2]}` : <></>}</div>
                        {/* <input type="data"/> */}
                    </div>

                    <div className="profile-detail-container">
                        <div className="profile-detail-title">이메일</div>
                        <div>{data.email}</div>
                        {/* <input type="text"/> */}
                    </div>

                    <div className="profile-detail-container">
                        <div className="profile-detail-title">전화번호</div>
                        <div>{data.phone}</div>
                        {/* <input type="text"/> */}
                    </div>

                </div>
            </div>


            <div className="profile-content">
                <div className="profile-detail-title">자기 소개</div>
                <div dangerouslySetInnerHTML={{__html: data.contentHtml}}></div>
            </div>

            <div className="edit-btn-container">
                <button className="edit-profile-btn">프로필 수정</button>
            </div>

        </div>
        }

        <div className="mypage-menu-container">

            <div className="mypage-menu" onClick={() => nav('/create-myShowcase')}>
                <div className="mypage-menu-title">쇼케이스 생성</div>
                <div>내가 만든 프로젝트와 기능을 자랑해보세요.<br /><br />
                    나의 프로젝트와 기능을 전시하여 다른 사람들에게 공유하고, 
                    포트폴리오처럼 정리한 결과물을 보여줄 수 있어요.
                </div>
            </div>

            <div className="mypage-menu"  onClick={() => nav('/manage-myShowcase')}>
                <div className="mypage-menu-title">쇼케이스 관리</div>
                <div>전시한 프로젝트와 기능을 관리하세요. <br /><br />
                    개선하거나 추가한 것이 있다면 업데이트해보세요. 
                </div>
            </div>

            <div className="mypage-menu">
                <div className="mypage-menu-title">아이디 / 비밀번호 찾기</div>
                <div>아이디는 어쩌구<br />
                    비밀번호는 이메일 인증을 통해 변경할 수 있습니다.
                </div>
            </div>
            

        </div>

    </div>


}

export default MyPage;