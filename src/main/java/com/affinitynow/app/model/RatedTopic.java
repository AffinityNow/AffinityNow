// package com.affinitynow.app.model;

// import javax.persistence.EmbeddedId;
// import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.MapsId;

// import com.affinitynow.app.utilisateur.dto.UtilisateurDto;

// @Entity
// public class RatedTopic {
//     @EmbeddedId
//     private RatedTopicKey id;

//     @ManyToOne
//     @MapsId("topicId")
//     @JoinColumn(name = "topic_id")
//     private Topic topic;

//     @ManyToOne
//     @MapsId("userId")
//     @JoinColumn(name = "user_id")
//     private Utilisateur user;

//     private  int rating;

//     public RatedTopic() {
//     }

//     public RatedTopicKey getId() {
//         return id;
//     }

//     public RatedTopic setId(RatedTopicKey id) {
//         this.id = id;
//         return this;
//     }

//     public Topic getTopic() {
//         return topic;
//     }

//     public RatedTopic setTopic(Topic topic) {
//         this.topic = topic;
//         return this;
//     }

//     public Utilisateur getUser() {
//         return user;
//     }

//     public RatedTopic setUser(Utilisateur user) {
//         this.user = user;
//         return this;
//     }

//     public int getRating() {
//         return rating;
//     }

//     public RatedTopic setRating(int rating) {
//         this.rating = rating;
//         return this;
//     }

//     @Override
//     public int hashCode() {
//         final int prime = 31;
//         int result = 1;
//         result = prime * result + ((id == null) ? 0 : id.hashCode());
//         return result;
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj)
//             return true;
//         if (obj == null)
//             return false;
//         if (getClass() != obj.getClass())
//             return false;
//         RatedTopic other = (RatedTopic) obj;
//         if (id == null) {
//             if (other.id != null)
//                 return false;
//         } else if (!id.equals(other.id))
//             return false;
//         return true;
//     }
// }
