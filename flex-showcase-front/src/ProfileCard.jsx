import "./ProfileCard.css";

const ProfileCard = () => {


    return(
        <div className="ProfileCard">
            <div style={{width: "300px",}}>사진</div>

            <div>
                <div>차윤하</div>
                <div>하고싶은 말</div>
                <div>주요 기술: 
                    <div>React, Spring Boot, Spring Data JPA</div>
                
                </div>
            </div>

        </div>

    )

}

export default ProfileCard;