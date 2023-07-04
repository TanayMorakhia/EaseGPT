package com.example.EaseGPT.model.request;

import java.util.List;

public class ReceivedMessage {
    private String object;
    private List<Entry> entry;

    public class Entry {
        private String id;
        private List<Change> changes;

        public class Change {
            private Value value;
            private String field;

            public class Value {
                private String messaging_product;
                private Metadata metadata;
                private List<Contact> contacts;
                private List<Message> messages;

                public class Metadata {
                    private String display_phone_number;
                    private String phone_number_id;

                    public String getDisplay_phone_number() {
                        return display_phone_number;
                    }

                    public String getPhone_number_id() {
                        return phone_number_id;
                    }

                }

                public class Contact {
                    private Profile profile;
                    private String wa_id;

                    public class Profile {
                        private String name;

                        public String getName() {
                            return name;
                        }

                    }

                    public String getWa_id() {
                        return wa_id;
                    }
                }

                public class Message {
                    private String from;
                    private String id;
                    private long timestamp;
                    private Text text;
                    private String type;

                    public static class Text {
                        private String body;

                        public String getBody() {
                            return body;
                        }
                    }

                    public String getFrom() {
                        return from;
                    }

                    public String getId() {
                        return id;
                    }

                    public long getTimestamp() {
                        return timestamp;
                    }

                    public String getType() {
                        return type;
                    }
                }

                // Getters and setters
                // ...
            }

            // Getters and setters
            // ...
        }

        // Getters and setters
        // ...
    }

    public String getBody(){
        return entry.get(0).changes.get(0).value.messages.get(0).text.body;
    }

}
