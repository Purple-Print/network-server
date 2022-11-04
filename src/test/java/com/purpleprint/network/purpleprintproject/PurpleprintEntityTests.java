package com.purpleprint.network.purpleprintproject;

import com.purpleprint.network.purpleprintproject.entities.*;
import com.purpleprint.network.purpleprintproject.entities.Character;
import com.purpleprint.network.purpleprintproject.entities.User;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurpleprintEntityTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @Test
    public void User_엔티티_생성_테스트() {

        User user = new User();
        user.setUsername("yee");
        user.setPwd("1234");
        user.setEmail("yee@naver.com");
        user.setName("하악찡");
        user.setRole(RoleType.MEMBER);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(user);
        entityTransaction.commit();

        User testUser = entityManager.find(User.class, 1);
        assertEquals(user.getId(), testUser.getId());
    }

    @Test
    public void Child_엔티티_생성_테스트() {

        Child child = new Child();
        child.setName("현정찡");
        child.setUserId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(child);
        entityTransaction.commit();

        Child testChild = entityManager.find(Child.class, 1);
        assertEquals(child.getId(), testChild.getId());
    }

    @Test
    public void Login_엔티티_생성_테스트(){

        Login login = new Login();
        login.setChildId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(login);
        entityTransaction.commit();

        Login testLogin = entityManager.find(Login.class, 1);
        assertEquals(login.getId(), testLogin.getId());
    }

    @Test
    public void Logout_엔티티_생성_테스트() {
        Logout logout = new Logout();
        logout.setxCoord(1);
        logout.setyCoord(1);
        logout.setzCoord(1);
        logout.setLoginId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(logout);
        entityTransaction.commit();

        Logout testLogout = entityManager.find(Logout.class, 1);
        assertEquals(logout.getId(), testLogout.getId());
    }

    @Test
    public void Video_엔티티_생성_테스트() {
        Video video = new Video();
        video.setUrl("videoUrl");
        video.setOriginalName("video1");
        video.setConversionName("convertedVideo1");
        video.setChildId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(video);
        entityTransaction.commit();

        Video testVideo = entityManager.find(Video.class, 1);
        assertEquals(video.getId(), testVideo.getId());
    }

    @Test
    public void Post_엔티티_생성_테스트() {
        Post post = new Post();
        post.setCompanionReason("반려 사유 블라블라");
        post.setVideoId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(post);
        entityTransaction.commit();

        Post testPost = entityManager.find(Post.class, 1);
        assertEquals(post.getId(), testPost.getId());
    }

    @Test
    public void Log_엔티티_생성_테스트() {
        Log log = new Log();
        log.setxCoord(1);
        log.setyCoord(1);
        log.setChildId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(log);
        entityTransaction.commit();

        Log testLog = entityManager.find(Log.class, 1);
        assertEquals(log.getId(), testLog.getId());
    }

    @Test
    public void Heart_엔티티_생성_테스트() {
        Heart heart = new Heart();
        heart.setGiver(1);
        heart.setRecipient(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(heart);
        entityTransaction.commit();

        Heart testHeart = entityManager.find(Heart.class, 1);
        assertEquals(heart.getId(), testHeart.getId());
    }

    @Test
    public void Character_엔티티_생성_테스트() {
        Character character = new Character();
        character.setChildId(1);
        character.setCharacterfileId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(character);
        entityTransaction.commit();

        Character testCharacter = entityManager.find(Character.class, 1);
        assertEquals(character.getId(), testCharacter.getId());
    }

    @Test
    public void Characterfile_엔티티_생성_테스트() {
        Characterfile characterfile = new Characterfile();
        characterfile.setUrl("url 블라블라");
        characterfile.setName("file name 블라블라");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(characterfile);
        entityTransaction.commit();

        Characterfile testCharacterfile = entityManager.find(Characterfile.class, 1);
        assertEquals(characterfile.getId(), testCharacterfile.getId());
    }

    @Test
    public void Analysis_엔티티_생성_테스트() {
        Analysis analysis = new Analysis();
        analysis.setBehaviorFeedback("행동교정 피드백 블라블라");
        analysis.setFinalFeedback("최종결과분석 피드백 블라블라");
        analysis.setChildId(1);
        analysis.setFriend1(1);
        analysis.setFriend2(2);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(analysis);
        entityTransaction.commit();

        Analysis testAnalysis = entityManager.find(Analysis.class, 1);
        assertEquals(analysis.getId(), testAnalysis.getId());
    }

    @Test
    public void AbnormalBehavior_엔티티_생성_테스트() {

        AbnormalBehavior abnormalBehavior = new AbnormalBehavior();
        abnormalBehavior.setPointing(1);
        abnormalBehavior.setJumping(5);
        abnormalBehavior.setPunching(3);
        abnormalBehavior.setChildId(1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(abnormalBehavior);
        entityTransaction.commit();

        AbnormalBehavior testAbnormalBehavior = entityManager.find(AbnormalBehavior.class, 1);
        assertEquals(abnormalBehavior.getId(), testAbnormalBehavior.getId());
    }

}
