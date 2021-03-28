package com.affinitynow.app.user.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.Set;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    private final static String TEST_USER_USERNAME = "jean";
    private final static Set<String> TEST_TOPICS = Collections.emptySet();
    private final static String TEST_STRATEGY = "scoreBool";
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetAllUsers() throws Exception {
        mvc.perform(get("/user")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        mvc.perform(get("/user/{username}", TEST_USER_USERNAME )
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetFollowsOfUser() throws Exception {
        mvc.perform(get("/user/{username}/follow", TEST_USER_USERNAME )
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetFriendsOfUser() throws Exception {
        mvc.perform(get("/user/{username}/friend", TEST_USER_USERNAME )
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetUserMatchingList() throws Exception {
        mvc.perform(post("/user/{username}/match/{strategyName}", TEST_USER_USERNAME,  TEST_STRATEGY)
            .contentType(MediaType.APPLICATION_JSON)
            .content("[]"))
            .andExpect(status().isOk())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateNewUserWithKnowledges() throws Exception {
        mvc.perform(post("/user/knowledges")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "    \"pseudo\": \"Sefkan\",\n" +
                    "    \"seekedKnowledges\": {},\n" +
                    "    \"likedKnowledges\": {},\n" +
                    "    \"email\": \"sefkan@gmail.com\"\n" +
                    "}"))
            .andExpect(status().isCreated())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddFollowToUserFollowList() throws Exception {
        mvc.perform(post("/user/{username}/follow", TEST_USER_USERNAME)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "\"pseudo\": \"Toto\"\n" +
                    " }"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddFriendToUserFriendList() throws Exception {
        mvc.perform(post("/user/{username}/friend", TEST_USER_USERNAME)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "\"pseudo\": \"Toto\"\n" +
                    " }"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemoveFollowFromUserFollowList() throws Exception {
        testAddFollowToUserFollowList();
        mvc.perform(delete("/user/{username}/friend", TEST_USER_USERNAME)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "\"pseudo\": \"Toto\"\n" +
                    " }"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRemoveFriendFromUserFriendList() throws Exception {
        testAddFriendToUserFriendList();
        mvc.perform(delete("/user/{username}/friend", TEST_USER_USERNAME)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "\"pseudo\": \"Toto\"\n" +
                    " }"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
