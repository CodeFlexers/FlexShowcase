import "./ProfileCard.css";

const ProfileCard = () => {


    return(
        <div className="ProfileCard">
            <img src="profile-image.jpg" alt="프로필 사진" className="profile-image" />

            <div className="profile-user-info">
                <div className="user-name">차윤하</div>
                <div>email | helena02282naver.com</div>
                <div>birth | 2000.02.28</div>
                <div>tech |
                    <div>React, Spring Boot, Spring Data JPA</div> 
                </div>
                <button className="contact-profile-btn">contact me</button>
            </div>

        </div>

    )

}

export default ProfileCard;