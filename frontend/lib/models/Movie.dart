class Movie {
  final String name;
  final String imagePath;
  final String categoty;
  final String videoPath;
  final int year;
  final Duration duration;

  const Movie(
      {required this.name,
      required this.imagePath,
      required this.categoty,
      required this.videoPath,
      required this.year,
      required this.duration});

  static const List<Movie> movies = [
    Movie(
        name: 'Hustle',
        imagePath:
            'https://sneakernews.com/wp-content/uploads/2022/09/Nike-Dunk-Low-DV0834-101-4.jpg',
        videoPath: '',
        year: 2022,
        duration: Duration(hours: 1, minutes: 58),
        categoty: 'Drama'),
    Movie(
        name: 'nnnnnn',
        imagePath:
            'https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/6b3cf66b-a3d2-4161-8979-ba835bab7449/scarpa-dunk-low-hbkBSL.png',
        videoPath: '',
        year: 2023,
        duration: Duration(hours: 7, minutes: 4),
        categoty: 'Happy'),Movie(
        name: 'Hustle',
        imagePath:
            'https://sneakernews.com/wp-content/uploads/2022/09/Nike-Dunk-Low-DV0834-101-4.jpg',
        videoPath: '',
        year: 2022,
        duration: Duration(hours: 1, minutes: 58),
        categoty: 'Drama'),
    Movie(
        name: 'nnnnnn',
        imagePath:
            'https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/6b3cf66b-a3d2-4161-8979-ba835bab7449/scarpa-dunk-low-hbkBSL.png',
        videoPath: '',
        year: 2023,
        duration: Duration(hours: 7, minutes: 4),
        categoty: 'Happy'),Movie(
        name: 'Hustle',
        imagePath:
            'https://sneakernews.com/wp-content/uploads/2022/09/Nike-Dunk-Low-DV0834-101-4.jpg',
        videoPath: '',
        year: 2022,
        duration: Duration(hours: 1, minutes: 58),
        categoty: 'Drama'),
    Movie(
        name: 'nnnnnn',
        imagePath:
            'https://static.nike.com/a/images/c_limit,w_592,f_auto/t_product_v1/6b3cf66b-a3d2-4161-8979-ba835bab7449/scarpa-dunk-low-hbkBSL.png',
        videoPath: '',
        year: 2023,
        duration: Duration(hours: 7, minutes: 4),
        categoty: 'Happy'),
  ];
}
