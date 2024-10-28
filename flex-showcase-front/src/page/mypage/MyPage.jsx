import { useEffect, useState } from "react";
import "./MyPage.css"
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "../../reducer/UserDataSlice";
import { useNavigate } from "react-router-dom";

const MyPage = () => {


    const {data, state, error} = useSelector((state)=>state.user);
    const dispatch = useDispatch();
    console.log(data,state,error);
    
    useEffect(() => {
        if(!data && state !== 'loading'){  
            //데이터가 없고, 로딩 중이 아닐 대 정보 불러옴 로딩 중 이유는 새로 고침 시 헤더랑 같이 요청하기 때문
            dispatch(getUser());
        }
    }, [])


    return <div className="mypage">

        {/* 프로필 수정 */}
        {data && 
        <div className="profile-info">

            <div className="profile-inf">
                <div className="profile-in">
                        <img
                            src={data.profileImage}
                            alt="프로필 사진"
                            className="profile-image"
                            onError={(e) => {e.target.src = './profile/default-profile.png'}}
                        />
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
                <h3>자유롭게 소개해주세요</h3>
                <div style={{background:'rgb(244,244,244)',borderRadius:10,padding:20}}>
                    <div dangerouslySetInnerHTML={{__html: data.contentHtml}}></div>
                </div>
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