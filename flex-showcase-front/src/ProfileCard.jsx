import "./ProfileCard.css";

const ProfileCard = ({member, setMember}) => {


    return(
        <div className="ProfileCard">
            <div style={{width: "300px",}}>사진</div>

            <div>
                <div>차윤하</div>
                <div>생일 2000.02.28</div>
                <div>주요 기술: 
                    <div>React, Spring Boot, Spring Data JPA</div>
                
                </div>
            </div>

        </div>

    )

}

export default ProfileCard;