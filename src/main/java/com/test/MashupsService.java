package com.test;

public class MashupsService {
/*
    private final String url = "http://localhost:8080/vf/employees";
    private final CloseableHttpClient httpClient = HttpClients.createSystem();
    private final int NUM_OF_REQUEST = 100;

    public void fireGetRequests() {
        System.out.println("Main thread is starting");

        List<Thread> threadLists = new ArrayList<>();

        for (int i = 0; i < NUM_OF_REQUEST; i++) {
            threadLists.add(new Thread(() -> {
                HttpGet httpGet = new HttpGet(url);
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    HttpEntity entity = response.getEntity();

                    responseProcess(entity.getContent());

                    //responseProcessNewWay(entity.getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }));
        }

        for (Thread t : threadLists) {
            t.start();
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Thread t : threadLists) {
            try {
                t.join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Main thread is finishing");
    }

    private void responseProcessNewWay(InputStream is) {
        String result = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            result = br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
    }

    private void responseProcess(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
*/
}
