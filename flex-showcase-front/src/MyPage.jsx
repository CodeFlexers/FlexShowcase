import "./MyPage.css"

const MyPage = () => {

    // 조회한 mypage 데이터 상태 변수 저장

    return <div className="mypage">

        <div className="profile-info">
            <div className="profile-inf">
                <div className="profile-in">
                        <img src="profile-image.jpg" alt="프로필 사진" className="profile-image" />
                        <div className="user-name">차윤하</div>
                        <div>email: user@example.com</div>
                        <div>phone: 010-0000-0000</div>
                </div>

                <div className="profile-edit">

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">닉네임</div>
                        <div>차차차</div>
                        {/* <input type="text"/> */}
                    </div>

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">생일</div>
                        <div>2000.02.29</div>
                        {/* <input type="data"/> */}
                    </div>

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">이메일</div>
                        <div>hhhh</div>
                        {/* <input type="text"/> */}
                    </div>

                    <div className="profile-edit-container">
                        <div className="profile-edit-title">전화번호</div>
                        <div>01000000000</div>
                        {/* <input type="text"/> */}
                    </div>

                </div>
            </div>

            <div className="profile-editer">
                <h3>자유롭게 소개해주세요</h3>
                <textarea placeholder="텍스트 에디터"/>
            </div>

            <div className="edit-btn-container">
                <button className="edit-profile-btn">프로필 수정</button>
            </div>

        </div>





    </div>


}

export default MyPage;