class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        buildGraph(times, graph, n);
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n + 1];
        dist[k] = 0;
        visited[k] = true;
        PriorityQueue<int[]> edgesPQ = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });
        edgesPQ.add(new int[]{k, 0});
        while (!edgesPQ.isEmpty()) {
            int node[] = edgesPQ.poll();
            visited[node[0]] = true;
            for (Map.Entry<Integer,Integer> map : graph.get(node[0]).entrySet()) {
                int nextNode = map.getKey();
                int value = map.getValue();
                if (visited[nextNode] || (dist[node[0]] + value) > dist[nextNode])
                    continue;
                dist[nextNode] = dist[node[0]] + value;
                edgesPQ.add(new int[]{nextNode, dist[nextNode]});
            }
        }
        int max = 0;
        for(int i = 1; i <=n; i++)
            max = Math.max(max, dist[i]);
        return max == Integer.MAX_VALUE ? -1 : max;
    }
    
    public void buildGraph(int[][] times, Map<Integer, Map<Integer, Integer>> graph, int n) {
        for(int i = 1; i <= n; i++)
            graph.put(i, new HashMap<>());
        for(int i = 0; i < times.length; i++) {
            graph.get(times[i][0]).put(times[i][1], times[i][2]);
        }
    }
}
