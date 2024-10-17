import "./ProfileCard.css";

const ProfileCard = ({user}) => {




    return(
        <div className="ProfileCard" style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
            <div style={{ display: "flex", alignItems: 'center' }}>
                <img
                    src={user.profileImage}
                    alt="프로필 사진"
                    className="profile-image"
                    onError={(e) => e.target.src = './profile/default-profile.png'}
                />
                <div className="profile-user-info">
                    <div className="user-name">{user.name}</div>
                    <div>email | {user.email}</div>
                    <div>birth | {user.birthDate ? `${user.birthDate[0]}.${user.birthDate[1]}.${user.birthDate[2]}` : ``}</div>
                </div>
            </div>

            <div style={{ flexGrow: 1, alignSelf: 'flex-start' }} dangerouslySetInnerHTML={{ __html: user.contentHTML }}></div>
            <button className="contact-profile-btn" style={{ alignSelf: 'center' }}>contact me</button>
        </div>
    )

}

export default ProfileCard;